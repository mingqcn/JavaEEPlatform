package cn.edu.xmu.webfluxdemo.controller;

import cn.edu.xmu.webfluxdemo.model.bo.FlashSaleItem;
import cn.edu.xmu.webfluxdemo.model.vo.FlashSaleItemRetVo;
import cn.edu.xmu.webfluxdemo.service.FlashSaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * @author Ming Qiu
 * @date Created in 2020/11/21 14:09
 **/
@RestController
@RequestMapping(path = "/flashsale", produces = "application/json;charset=UTF-8")
public class FlashSaleController {

    private  static Logger logger = LoggerFactory.getLogger(FlashSaleController.class);

    @Autowired
    private FlashSaleService flashSaleService;

    @GetMapping(path = "timesegments/{id}/flashsales")
    public Flux<FlashSaleItemRetVo> getFlashSale(@PathVariable Long id) {
        return flashSaleService.getFlashSale(id).map(x -> (FlashSaleItemRetVo) x.createVo());
    }
}
