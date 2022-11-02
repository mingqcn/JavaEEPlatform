//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.dao;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.util.RedisUtil;
import cn.edu.xmu.oomall.payment.dao.bo.Channel;
import cn.edu.xmu.oomall.payment.mapper.generator.ChannelPoMapper;
import cn.edu.xmu.oomall.payment.mapper.generator.po.ChannelPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static cn.edu.xmu.javaee.core.util.Common.cloneObj;

@Repository
public class ChannelDao{

    private Logger logger = LoggerFactory.getLogger(ChannelDao.class);

    public static final String KEY = "C%d";

    private RedisUtil redisUtil;

    private ChannelPoMapper channelPoMapper;

    @Autowired
    public ChannelDao(RedisUtil redisUtil, ChannelPoMapper channelPoMapper) {
        this.redisUtil = redisUtil;
        this.channelPoMapper = channelPoMapper;
    }

    public Channel findObjById(Long id) throws RuntimeException {
        Channel channel = null;
        String key = String.format(KEY, id);
        if (redisUtil.hasKey(key)) {
            channel = (Channel) redisUtil.get(key);
        } else {
            ChannelPo po = this.channelPoMapper.selectByPrimaryKey(id);
            if (null == po){
                throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST, String.format(ReturnNo.RESOURCE_ID_NOTEXIST.getMessage(),"支付渠道", id));
            }
            channel = cloneObj(po, Channel.class);
            //永不过期
            redisUtil.set(key, channel, -1);
        }
        return channel;
    }

}
