//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.dao;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.util.RedisUtil;
import cn.edu.xmu.oomall.payment.dao.bo.Business;
import cn.edu.xmu.oomall.payment.mapper.generator.BusinessPoMapper;
import cn.edu.xmu.oomall.payment.mapper.generator.po.BusinessPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import static cn.edu.xmu.javaee.core.util.Common.*;

@Repository
public class BusinessDao {

    private Logger logger = LoggerFactory.getLogger(BusinessDao.class);

    private static final String KEY = "B%d";

    private BusinessPoMapper businessPoMapper;

    private RedisUtil redisUtil;

    @Autowired
    public BusinessDao(BusinessPoMapper businessPoMapper, RedisUtil redisUtil) {
        this.businessPoMapper = businessPoMapper;
        this.redisUtil = redisUtil;
    }

    public Business findObjById(Long id) throws RuntimeException{
        Business b = null;
        String key = String.format(KEY, id);
        if (redisUtil.hasKey(key)) {
            b = (Business) redisUtil.get(key);
        }else {
                BusinessPo po = businessPoMapper.selectByPrimaryKey(id);
                b = cloneObj(po, Business.class);
                redisUtil.set(key,b , -1);
        }
        return b;
    }
}
