package cn.edu.xmu.webfluxdemo.service;

import cn.edu.xmu.webfluxdemo.model.bo.FlashSaleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.Serializable;

/**
 * @author Ming Qiu
 * @date Created in 2020/11/21 15:00
 **/
@Service
public class FlashSaleService {

    @Autowired
    private ReactiveRedisTemplate<String, Serializable> reactiveRedisTemplate;
    public Flux<FlashSaleItem> getFlashSale(Long segId){
        return reactiveRedisTemplate.opsForSet().members(segId.toString()).map(x-> (FlashSaleItem) x);
    }

}
