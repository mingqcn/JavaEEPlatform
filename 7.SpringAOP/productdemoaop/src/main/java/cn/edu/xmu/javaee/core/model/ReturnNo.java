//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回的错误码
 * @author Ming Qiu
 */
public enum ReturnNo {
    /***************************************************
     *    系统级返回码
     **************************************************/

    //状态码 200
    OK(0,"成功"),
    CREATED(1, "创建成功"),
    STATENOTALLOW(7,"%s对象（id=%d）%s状态禁止此操作"),
    RESOURCE_FALSIFY(11, "信息签名不正确"),

    //状态码 404
    RESOURCE_ID_NOTEXIST(4,"%s对象(id=%d)不存在"),

    //状态码 500
    INTERNAL_SERVER_ERR(2,"服务器内部错误"),
    APPLICATION_PARAM_ERR(20, "服务器配置参数(%s)错误"),

    //所有需要登录才能访问的API都可能会返回以下错误
    //状态码 400
    FIELD_NOTVALID(3,"%s字段不合法"),
    IMG_FORMAT_ERROR(8,"图片格式不正确"),
    IMG_SIZE_EXCEED(9,"图片大小超限"),
    PARAMETER_MISSED(10, "缺少必要参数"),
    INCONSISTENT_DATA(20,"数据不一致"),

    //状态码 401
    AUTH_INVALID_JWT(5,"JWT不合法"),
    AUTH_JWT_EXPIRED(6,"JWT过期"),
    AUTH_INVALID_ACCOUNT(12, "用户名不存在或者密码错误"),
    AUTH_ID_NOTEXIST(13,"登录用户id不存在"),
    AUTH_USER_FORBIDDEN(14,"用户被禁止登录"),
    AUTH_NEED_LOGIN(15, "需要先登录"),

    //状态码 403
    AUTH_NO_RIGHT(16, "无权限"),
    RESOURCE_ID_OUTSCOPE(17,"%s对象(id=%d)超出商铺（id = %d）的操作范围"),
    FILE_NO_WRITE_PERMISSION(18,"目录文件夹没有写入的权限"),

    /**************************************
     *  支付模块
     ************************************/
    PAY_CHANNEL_INVALID(102,"%s支付渠道无效"),
    PAY_CHANNEL_EXIST(103,"商铺(id=%d)的支付渠道(id=%d)已经存在"),
    PAY_REFUND_MORE(104,"退款金额超过支付对象(id=%d)的金额"),
    PAY_DIVREFUND_MORE(105,"分账退回金额超过支付对象(id=%d)的支付分账金额"),

    /***************************************************
     *    商铺模块错误码
     **************************************************/
    SHOP_USER_HASSHOP(199,"用户(id= %d)已经有店铺"),
    FREIGHT_REGION_NOTREACH(198,"该地区不可达（包括暂停和不在配送范围）"),
    FREIGHT_REGIONOBSOLETE(197,"地区已废弃"),
    FREIGHT_REGIONEXIST(195,"该运费模板中该地区(id=%d)已经定义"),


    /***************************************************
     *    商品模块错误码
     **************************************************/
    CATEGORY_NOTPERMIT(201, "分类(id = %d)不允许增加新的下级分类"),
    CATEGORY_SAME(202, "类目(id = %d)名称已存在"),
    CATEGORY_NOTALLOW(203, "商品不允许加入到一级分类(id = %d)"),


    SHARE_UNSHARABLE(214, "货品不可分享"),
    ADVSALE_NOTCOEXIST(215, "预售不能与销售(id=%d)中的其他活动共存"),
    GROUPON_NOTCOEXIST(216, "团购不能与销售(id=%d)中的其他活动共存"),

    GOODS_STOCK_SHORTAGE(296,"货品（id = %d）库存不足"),
    GOODS_ONSALE_NOTEFFECTIVE(297, "货品(id=%d)不在有效的销售状态和时间"),
    GOODS_ONSALE_CONFLICT(299,"与销售(id = %d)时间冲突"),

    LATE_BEGINTIME(19, "开始时间不能晚于结束时间"),
    ADV_SALE_TIMELATE(220, "尾款支付时间晚于活动结束时间"),
    ADV_SALE_TIMEEARLY(221, "尾款支付时间早于活动开始时间"),
    COUPON_TIMELATE(217, "优惠卷领卷时间晚于活动开始时间"),
    /***************************************************
     *    顾客模块错误码
     **************************************************/
    ADDRESS_OUTLIMIT(601,"达到地址簿上限"),

    ORDERITEM_NOTSHARED(606,"订单明细无分享记录"),
    CUSTOMERID_NOTEXIST(608,"登录用户id不存在"),
    CUSTOMER_INVALID_ACCOUNT(609, "用户名不存在或者密码错误"),
    CUSTOMER_FORBIDDEN(610,"用户被禁止登录"),
    CUSTOMER_NAMEEXIST(611,"用户名已被注册"),
    CUSTOMER_PASSWORDSAME(612,"不能与旧密码相同"),
    CUSTOMER_MOBILEEXIST(613,"电话已被注册"),
    CUSTOMER_MOBILEDIFF(614,"与系统预留的电话不一致"),
    CUSTOMER_CARTNOTALLOW(615,"商品(id = %d)不能加入购物车"),

    COUPON_NOTBEGIN(696,"未到优惠卷领取时间"),
    COUPON_FINISH(697,"优惠卷领罄"),
    COUPON_END(698,"优惠卷活动终止"),
    COUPON_EXIST(699,"不可重复领优惠卷"),
    /**************************************
     *  售后模块
     ************************************/
    ARBITRATION_NOTSELF(701, "仲裁(id=%d)非本用户受理的仲裁"),
    ARBITRATION_NOT_APPLICANT(702, "仲裁(id=%d)仅申请人可以撤销"),
    AFTERSALE_NOT_RETURNCHANGE(703, "(id=%d)不是退换货售后"),
    AFTERSALE_NOT_SELFSENDBACK(704, "(id=%d)不是自行寄回售后"),
    AFTERSALE_INARBITRATION(705, "售后(id=%d)在仲裁中"),

    /**************************************
     *  服务模块
     ************************************/


    /***************************************************
     *    订单模块错误码
     **************************************************/
    ORDER_CHANGENOTALLOW(801,"订单(id=%d)地址费用变化"),
    ITEM_OVERMAXQUANTITY(802,"销售对象(id=%d)的数量(%d)操过单次可购买数量(%d)"),


    /**************************************
     *  地区模块
     ************************************/
    REGION_ABANDONE(901, "地区(id=%d)已废弃"),

    /**************************************
     *  物流模块
     ************************************/
    FREIGHT_WAREHOUSEREGION_EXIST(997, "重复设置地区"),
    FREIGHT_WAREHOUSELOGISTIC_EXIST(998, "重复设置物流"),
    FREIGHT_LOGISTIC_EXIST(999, "商铺已存在物流(id=%d)");


    private int errNo;
    private String message;
    private static final Map<Integer, ReturnNo> returnNoMap = new HashMap() {
        {
            for (Object enum1 : values()) {
                put(((ReturnNo) enum1).errNo, enum1);
            }
        }
    };

    ReturnNo(int code, String message){
        this.errNo = code;
        this.message = message;
    }

    public static ReturnNo getByCode(int code1) {
        ReturnNo[] all=ReturnNo.values();
        for (ReturnNo returnNo :all) {
            if (returnNo.errNo==code1) {
                return returnNo;
            }
        }
        return null;
    }
    public static ReturnNo getReturnNoByCode(int code){
        return returnNoMap.get(code);
    }
    public int getErrNo() {
        return errNo;
    }

    public String getMessage(){
        return message;
    }


    }
