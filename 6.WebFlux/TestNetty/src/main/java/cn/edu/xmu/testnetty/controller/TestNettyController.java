package cn.edu.xmu.testnetty.controller;

import cn.edu.xmu.testnetty.util.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author Ming Qiu
 * @date Created in 2021-6-1 22:31
 **/
@RequestMapping(value = "/testnetty", produces = "application/json;charset=UTF-8")
public class TestNettyController {

    @GetMapping("{latency}")
    public Mono<Object> sleepthread(@PathVariable long latency){
        return Mono.just(ResponseUtil.ok()).delayElement(Duration.ofSeconds(latency));
    }
}
