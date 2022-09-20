package cn.edu.xmu.rocketmqdemo.controller;

import cn.edu.xmu.rocketmqdemo.model.Goods;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.edu.xmu.rocketmqdemo.service.GoodsService;
import cn.edu.xmu.rocketmqdemo.util.ResponseUtil;

/**
 * 商品控制器
 * @author Ming Qiu
 */

@RestController /*Restful的Controller对象*/
@RequestMapping(value = "/goods", produces = "application/json;charset=UTF-8")
public class GoodsController {

    private final Log logger = LogFactory.getLog(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @GetMapping("{id}")
    public Object getGoodsById(@PathVariable("id") Integer id) {

        Goods goods = goodsService.findById(id);
        return ResponseUtil.ok(goods);
    }

}
