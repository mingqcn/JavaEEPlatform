package cn.edu.xmu.rocketmqdemo.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


/**
 * Redis工具类
 * @author Ming Qiu
 **/
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    /**
    * 删除缓存
    *
    * @param key 可以传一个值 或多个
    */

    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Serializable get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
    * 普通缓存放入
    *
    * @param key   键
    * @param value 值
    * @return true成功 false失败
    */
    public  boolean set(String key, Serializable value, long timeout) {
        if (timeout <= 0) {
            timeout = 60;
        }

        long min = 1;
        long max = timeout / 5;
        try {
            //增加随机数，防止雪崩
            timeout += (long) new Random().nextDouble() * (max - min);
            redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
    * 递减
    * @param key   键
    * @param delta 要减少几(小于0)
    * @return
    */
    public long decr(String key, long delta) {
        if (delta > 0) {
            return redisTemplate.opsForValue().increment(key, -delta);
        }else {
            return 0;
        }
    }
}
