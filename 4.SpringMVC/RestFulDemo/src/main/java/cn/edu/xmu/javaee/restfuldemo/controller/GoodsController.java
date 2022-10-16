package cn.edu.xmu.javaee.restfuldemo.controller;

import cn.edu.xmu.javaee.restfuldemo.controller.vo.GoodsVo;
import cn.edu.xmu.javaee.restfuldemo.service.GoodsService;
import cn.edu.xmu.javaee.restfuldemo.service.bo.Goods;
import cn.edu.xmu.javaee.restfuldemo.util.ResponseUtil;


import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 商品控制器
 * @author Ming Qiu
 */
@RestController /*Restful的Controller对象*/
@RequestMapping(value = "/goods", produces = "application/json;charset=UTF-8")
public class GoodsController {

    private final Log logger = LogFactory.getLog(GoodsController.class);

    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("{id}")
    public Object getGoodsById(@PathVariable("id") Integer id) {

        Goods goods = goodsService.findById(id);
        return ResponseUtil.ok(goods);
    }

    @GetMapping("")
    public Object searchGoodsByName(@RequestParam String name) {

        Goods goods = goodsService.searchByName(name);
        return ResponseUtil.ok(goods);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createGood(@RequestBody GoodsVo goodsVo){
        Goods goods = goodsVo.createGoods();
        Goods new_goods = goodsService.createGoods(goods);
        return ResponseUtil.ok(new_goods);
    }

    @PutMapping("{id}")
    public Object modiGood(@PathVariable Integer id, @RequestBody GoodsVo goodsVo){
        Goods goods = goodsVo.createGoods();
        goodsService.modifyGoods(id, goods);
        return ResponseUtil.ok();
    }

    @DeleteMapping("{id}")
    public Object delGoods(@PathVariable("id") Integer id) {

        goodsService.delGoods(id);
        return ResponseUtil.ok();
    }
}
