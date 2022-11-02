//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.dao;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.util.RedisUtil;
import cn.edu.xmu.oomall.payment.dao.bo.ShopChannel;
import cn.edu.xmu.oomall.payment.mapper.generator.ShopChannelPoMapper;
import cn.edu.xmu.oomall.payment.mapper.generator.po.ShopChannelPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import static cn.edu.xmu.javaee.core.util.Common.*;

/**
 * ShopChannel的dao对象
 */
@Repository
public class ShopChannelDao{

    private Logger logger = LoggerFactory.getLogger(ShopChannelDao.class);

    private static final String KEY = "SC%d";

    @Value("${oomall.payment.shopchannel.timeout}")
    private long timeout;

    private ShopChannelPoMapper shopChannelPoMapper;

    private RedisUtil redisUtil;

    private ChannelDao channelDao;

    @Autowired
    public ShopChannelDao(ShopChannelPoMapper shopChannelPoMapper, RedisUtil redisUtil, ChannelDao channelDao) {
        this.shopChannelPoMapper = shopChannelPoMapper;
        this.redisUtil = redisUtil;
        this.channelDao = channelDao;
    }

    public ShopChannel findObjById(Long id) throws RuntimeException {
        ShopChannel ret = null;
        String key = String.format(KEY, id);
        if (redisUtil.hasKey(key)) {
            ret = (ShopChannel) redisUtil.get(key);
        } else {
            ShopChannelPo po = shopChannelPoMapper.selectByPrimaryKey(id);
            ret = cloneObj(po, ShopChannel.class);
            redisUtil.set(key, ret, timeout);
        }
        ret.setChannelDao(this.channelDao);
        return ret;
    }
}
