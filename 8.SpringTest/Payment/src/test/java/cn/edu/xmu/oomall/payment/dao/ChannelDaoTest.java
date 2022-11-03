//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.dao;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.util.RedisUtil;
import cn.edu.xmu.oomall.payment.PaymentApplication;
import cn.edu.xmu.oomall.payment.dao.ChannelDao;
import cn.edu.xmu.oomall.payment.dao.bo.Channel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PaymentApplication.class)
@Transactional
public class ChannelDaoTest {

    @MockBean
    private RedisUtil redisUtil;

    @Autowired
    private ChannelDao channelDao;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void findObjById1(){
        Channel channel = new Channel();
        channel.setId(Long.valueOf(501));
        channel.setBeanName("wePayChannel");
        channel.setAppid("1");
        channel.setBeginTime(LocalDateTime.parse("2021-10-01 12:00:00", formatter));
        channel.setEndTime(LocalDateTime.parse("2023-10-01 12:00:00", formatter));
        channel.setName("微信支付");
        channel.setSpMchid("10000");
        channel.setStatus(Channel.VALID);
        channel.setNotifyUrl("http://111");

        Mockito.when(redisUtil.hasKey(String.format(ChannelDao.KEY, 501))).thenReturn(true);
        Mockito.when(redisUtil.get(String.format(ChannelDao.KEY, 501))).thenReturn(channel);

        Channel retChannel = channelDao.findObjById(Long.valueOf(501));

        assertThat(retChannel.getName()).isEqualTo(channel.getName());
        assertThat(retChannel.getId()).isEqualTo(Long.valueOf(501));
        assertThat(retChannel.getAppid()).isEqualTo(channel.getAppid());
    }

    @Test
    public void findObjById2(){
        Mockito.when(redisUtil.hasKey(String.format(ChannelDao.KEY, 501))).thenReturn(false);

        Channel retChannel = channelDao.findObjById(Long.valueOf(501));

        assertThat(retChannel.getName()).isEqualTo("微信支付");
        assertThat(retChannel.getId()).isEqualTo(Long.valueOf(501));
        assertThat(retChannel.getAppid()).isEqualTo("100001");
    }

    @Test
    public void findObjById3(){
        Mockito.when(redisUtil.hasKey(String.format(ChannelDao.KEY, 5))).thenReturn(false);
        assertThrows(BusinessException.class, ()-> channelDao.findObjById(Long.valueOf(5)));
    }

}
