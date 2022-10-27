//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.util;

import cn.edu.xmu.javaee.core.model.PageObj;
import cn.edu.xmu.javaee.core.model.SimpleUser;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 通用工具类
 *
 * @author Ming Qiu
 **/
public class Common {
    private static Logger logger = LoggerFactory.getLogger(Common.class);

    /**
     * 生成九位数序号
     * 要保证同一服务的不同实例生成出的序号是不同的
     * @param  platform 机器号 如果一个服务有多个实例，机器号需不同，目前从1至36
     * @return 序号
     */
    public static String genSeqNum(int platform) {
        int maxNum = 36;
        int i;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssS");
        LocalDateTime localDateTime = LocalDateTime.now();
        String strDate = localDateTime.format(dtf);
        StringBuffer sb = new StringBuffer(strDate);

        int count = 0;
        char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Random r = new Random();
        while (count < 2) {
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                sb.append(str[i]);
                count++;
            }
        }
        if (platform > 36){
            platform = 36;
        } else if (platform < 1){
            platform = 1;
        }

        sb.append(str[platform-1]);
        return sb.toString();
    }

    /**
     * 将list对象转换承list对象
     * @author xucangbai
     * @param objs
     * @param voClass
     * @return
     */
    public static <T> List<T> createListObj(List<? extends Object> objs,Class<T> voClass)
    {
        List<T> ret = null;
        if (null != objs) {
            ret = new ArrayList<>(objs.size());
            for (Object data : objs) {
                ret.add(cloneObj(data, voClass));
            }
        }
        return ret;
    }

    /**
     * 将带PageInfo的对象转换为普通的对象
     * @param pageObjs
     * @param targetClass
     * @return
     * @author xucangbai
     */
    public static <T> PageObj<T> createPageObj(PageInfo<? extends Object> pageObjs , Class<T> targetClass) {
        if (null == pageObjs){
            return null;
        }
        List<Object> voObjs = new ArrayList<>(pageObjs.getList().size());
        for (Object data : pageObjs.getList()) {
            voObjs.add(cloneObj(data, targetClass));
        }
        return new PageObj(voObjs, pageObjs.getTotal(), pageObjs.getPageNum(),pageObjs.getPageSize(),pageObjs.getPages());
    }

    /**
     * @param bo      business object
     * @param voClass vo对象类型
     * @return 浅克隆的vo对象
     * @author xucangbai
     * @date 2021/11/13
     * 根据clazz实例化一个对象，并深度克隆bo中对应属性到这个新对象
     * 其中会自动实现modifiedBy和createdBy两字段的类型转换
     */
    public static <T> T cloneObj(Object bo, Class<T> voClass) {
        Class boClass = bo.getClass();
        T newVo = null;
        try {
            //默认voClass有无参构造函数
            newVo = voClass.getDeclaredConstructor().newInstance();
            Field[] voFields = voClass.getDeclaredFields();
            Field[] boFields = boClass.getDeclaredFields();
            for (Field voField : voFields) {
                //静态和Final不能拷贝
                int mod = voField.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                voField.setAccessible(true);
                Field boField = null;
                try {
                    boField = boClass.getDeclaredField(voField.getName());
                    boField.setAccessible(true);
                    Class<?> boFieldType = boField.getType();
                    //属性名相同，类型相同，直接克隆
                    if (voField.getType().equals(boFieldType)) {
                        boField.setAccessible(true);
                        Object newObject = boField.get(bo);
                        voField.set(newVo, newObject);
                    }
                    //属性名相同，类型不同
                    else {
                        boolean boFieldIsIntegerOrByteAndVoFieldIsEnum = ("Integer".equals(boFieldType.getSimpleName()) || "Byte".equals(boFieldType.getSimpleName())) && voField.getType().isEnum();
                        boolean voFieldIsIntegerOrByteAndBoFieldIsEnum = ("Integer".equals(voField.getType().getSimpleName()) || "Byte".equals(voField.getType().getSimpleName())) && boFieldType.isEnum();
                        boolean voFieldIsLocalDateTimeAndAndBoFieldIsZonedDateTime = ("LocalDateTime".equals(voField.getType().getSimpleName()) && "ZonedDateTime".equals(boField.getType().getSimpleName()));
                        boolean voFieldIsZonedDateTimeAndBoFieldIsLocalDateTime = ("ZonedDateTime".equals(voField.getType().getSimpleName()) && "LocalDateTime".equals(boField.getType().getSimpleName()));

                        try{
                            //整形或Byte转枚举
                            if (boFieldIsIntegerOrByteAndVoFieldIsEnum) {
                                Object newObj = boField.get(bo);
                                if ("Byte".equals(boFieldType.getSimpleName())) {
                                    newObj = ((Byte) newObj).intValue();
                                }
                                Object[] enumer = voField.getType().getEnumConstants();
                                voField.set(newVo, enumer[(int) newObj]);
                            }
                            //枚举转整形或Byte
                            else if (voFieldIsIntegerOrByteAndBoFieldIsEnum) {
                                Object value = ((Enum) boField.get(bo)).ordinal();
                                if ("Byte".equals(voField.getType().getSimpleName())) {
                                    value = ((Integer) value).byteValue();
                                }
                                voField.set(newVo, value);
                            }
                            //ZonedDateTime转LocalDateTime
                            else if(voFieldIsLocalDateTimeAndAndBoFieldIsZonedDateTime)
                            {
                                ZonedDateTime newObj = (ZonedDateTime) boField.get(bo);
                                LocalDateTime localDateTime = newObj.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                                voField.set(newVo, localDateTime);
                            }
                            //LocalDateTime转ZonedDateTime
                            else if(voFieldIsZonedDateTimeAndBoFieldIsLocalDateTime)
                            {
                                LocalDateTime newObj = (LocalDateTime) boField.get(bo);
                                ZonedDateTime zdt = newObj.atZone( ZoneId.systemDefault() );
                                voField.set(newVo, zdt);
                            }
                            else {
                                voField.set(newVo, null);
                            }
                        }
                        //如果为空字段则不复制
                        catch (Exception e)
                        {
                            voField.set(newVo, null);
                        }
                    }
                }
                //bo中查找不到对应的属性，那就有可能为特殊情况xxx，需要由xxxId与xxxName组装
                catch (NoSuchFieldException e) {
                    //默认设成null
                    voField.set(newVo, null);
                    //提取头部
                    String head = voField.getName();
                    logger.debug("cloneObj: voField = {} not found in vo", head);
                    Field boxxxNameField = null;
                    Field boxxxIdField = null;
                    for (Field bof : boFields) {
                        if (bof.getName().matches(head + "Name")) {
                            boxxxNameField = bof;
                        } else if (bof.getName().matches(head + "Id")) {
                            boxxxIdField = bof;
                        }
                    }
                    logger.debug("cloneObj: boxxxIdField = {}, boxxxNameField = {} ", boxxxIdField, boxxxNameField);
                    //xxName和xxId属性均存在
                    if (null != boxxxNameField  && null != boxxxIdField) {
                        //bo的xxxId和xxxName组装为SimpleRetVo的id,name
                        boxxxIdField.setAccessible(true);
                        boxxxNameField.setAccessible(true);
                        Object boxxxId = boxxxIdField.get(bo);
                        Object boxxxName = boxxxNameField.get(bo);

                        //这两个属性不为空
                        if (null != boxxxId  || null != boxxxName) {
                            Object newSimpleRetVo = voField.getType().getDeclaredConstructor().newInstance();
                            Field newSimpleRetVoIdField = newSimpleRetVo.getClass().getDeclaredField("id");
                            Field newSimpleRetVoNameField = newSimpleRetVo.getClass().getDeclaredField("name");
                            newSimpleRetVoIdField.setAccessible(true);
                            newSimpleRetVoNameField.setAccessible(true);

                            newSimpleRetVoIdField.set(newSimpleRetVo, boxxxId);
                            newSimpleRetVoNameField.set(newSimpleRetVo, boxxxName);
                            voField.set(newVo, newSimpleRetVo);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return newVo;
    }

    /**
     * 将source对象的值拷贝到target
     * @param source
     * @param target
     * @param <T>
     * createdBy 蒋欣雨 2021/12/1
     * modifiedBy Ming Qiu 2021/12/12 9:28
     */

    public static <T> void copyAttribute(Object source, T target) {
        Class sourceClass = source.getClass();
        Class targetClass = target.getClass();
        try {
            Field[] sourceFields = sourceClass.getDeclaredFields();
            for (Field sourceField : sourceFields) {
                sourceField.setAccessible(true);
                //若修改的字段为空，则说明不修改该字段
                if (sourceField.get(source) == null)
                    continue;
                Field targetField = null;
                try {
                    targetField = targetClass.getDeclaredField(sourceField.getName());
                } catch (NoSuchFieldException e) {
                    continue;
                }

                //静态和Final不能拷贝
                int mod = targetField.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                targetField.setAccessible(true);

                Class<?> sourceFieldType = sourceField.getType();
                Class<?> targetFieldType = targetField.getType();
                //属性名相同，类型相同，直接克隆
                if (targetFieldType.equals(sourceFieldType)) {
                    Object newObject = sourceField.get(source);
                    if (newObject != null) {
                        targetField.set(target, newObject);
                    }
                }
                //属性名相同，类型不同
                else {
                    boolean sourceFieldIsIntegerOrByteAndTargetFieldIsEnum = ("Integer".equals(sourceFieldType.getSimpleName()) || "Byte".equals(sourceFieldType.getSimpleName())) && targetFieldType.isEnum();
                    boolean targetFieldIsIntegerOrByteAndSourceFieldIsEnum = ("Integer".equals(targetFieldType.getSimpleName()) || "Byte".equals(targetFieldType.getSimpleName())) && sourceFieldType.isEnum();
                    //整形或Byte转枚举
                    if (sourceFieldIsIntegerOrByteAndTargetFieldIsEnum) {
                        Object newObj = sourceField.get(source);
                        if ("Byte".equals(sourceFieldType.getSimpleName())) {
                            newObj = ((Byte) newObj).intValue();
                        }
                        Object[] enumer = targetFieldType.getEnumConstants();
                        targetField.set(target, enumer[(int) newObj]);
                    }
                    //枚举转整形或Byte
                    else if (targetFieldIsIntegerOrByteAndSourceFieldIsEnum) {
                        Object value = ((Enum) sourceField.get(source)).ordinal();
                        if ("Byte".equals(targetFieldType.getSimpleName())) {
                            value = ((Integer) value).byteValue();
                        }
                        targetField.set(target, value);
                    }

                }
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    /**
     * 设置所有po对象的creator， gmtCreaTe字段属性
     *
     * @param po       po对象
     * @param user   设置到modifiedBy
     * @return 如果po对象没有这些属性或类型不对返回false，否则true
     * @author : Wangzixia 32420182202938
     * @date： 2021/11/19 00:12
     * @version: 2.0
     */
    public static boolean putPoCreatedFields(Object po, SimpleUser user) {
        Class<?> aClass = po.getClass();
        try {
            Field creatorId = aClass.getDeclaredField("creatorId");
            creatorId.setAccessible(true);
            creatorId.set(po, user.getId());

        } catch (NoSuchFieldException e) {
            logger.info(e.getMessage());
            return false;
        } catch (IllegalAccessException ex) {
            logger.info(ex.getMessage());
            return false;
        }

        try {
            Field creatorName = aClass.getDeclaredField("creatorName");
            creatorName.setAccessible(true);
            creatorName.set(po, user.getName());
        } catch (NoSuchFieldException e) {
            logger.info(e.getMessage());
            return false;
        } catch (IllegalAccessException ex) {
            logger.info(ex.getMessage());
            return false;
        }
        try {
            Field createName = aClass.getDeclaredField("gmtCreate");
            createName.setAccessible(true);
            createName.set(po, LocalDateTime.now());
        } catch (NoSuchFieldException e) {
            logger.info(e.getMessage());
            return false;
        } catch (IllegalAccessException ex) {
            logger.info(ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 设置所有po对象的modifier, gmtModified字段属性
     *
     * @param po       po对象
     * @param user   设置到modifiedBy
     * @return 如果po对象没有这些属性或类型不对返回false，否则true
     * @author : Wangzixia 32420182202938
     * @date： 2021/11/19 00:12
     * @version: 2.0
     */
    public static boolean putPoModifiedFields(Object po, SimpleUser user) {
        Class<?> aClass = po.getClass();
        try {
            Field modifierId = aClass.getDeclaredField("modifierId");
            modifierId.setAccessible(true);
            modifierId.set(po, user.getId());
        } catch (NoSuchFieldException e) {
            logger.info(e.getMessage());
            return false;
        } catch (IllegalAccessException ex) {
            logger.info(ex.getMessage());
            return false;
        }

        try {
            Field modifierName = aClass.getDeclaredField("modifierName");
            modifierName.setAccessible(true);
            modifierName.set(po, user.getName());
        } catch (NoSuchFieldException e) {
            logger.info(e.getMessage());
            return false;
        } catch (IllegalAccessException ex) {
            logger.info(ex.getMessage());
            return false;
        }
        try {
            Field gmtModified = aClass.getDeclaredField("gmtModified");
            gmtModified.setAccessible(true);
            gmtModified.set(po, LocalDateTime.now());
        } catch (NoSuchFieldException e) {
            logger.info(e.getMessage());
            return false;
        } catch (IllegalAccessException ex) {
            logger.info(ex.getMessage());
            return false;
        }
        return true;
    }
}
