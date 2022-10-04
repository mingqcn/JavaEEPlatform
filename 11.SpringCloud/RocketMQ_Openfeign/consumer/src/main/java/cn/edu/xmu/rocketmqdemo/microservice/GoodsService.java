package cn.edu.xmu.rocketmqdemo.microservice;

import cn.edu.xmu.rocketmqdemo.model.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "freight-service", url = "http://localhost:8081")
public interface GoodsService {

    @GetMapping("/goods/{id}")
    Goods getGoodsById(@PathVariable Long id);

}
