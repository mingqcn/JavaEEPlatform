package cn.edu.xmu.testnetty.controller;

import cn.edu.xmu.testnetty.util.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Ming Qiu
 * @date Created in 2021-6-1 22:12
 **/
@RestController
@RequestMapping(value = "/testtomcat", produces = "application/json;charset=UTF-8")
public class TestTomcatController {
    @GetMapping("{latency}")
    public Object sleepthread(@PathVariable long latency){
        try {
            TimeUnit.SECONDS.sleep(latency);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseUtil.ok();
    }
}
