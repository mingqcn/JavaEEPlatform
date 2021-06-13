package cn.edu.xmu.restfuldemo.controller;

import cn.edu.xmu.restfuldemo.controller.vo.GoodsVo;
import cn.edu.xmu.restfuldemo.model.Goods;
import cn.edu.xmu.restfuldemo.service.GoodsService;
import cn.edu.xmu.restfuldemo.util.ResponseCode;
import cn.edu.xmu.restfuldemo.util.ResponseUtil;
import cn.edu.xmu.restfuldemo.util.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 手写校验输入合法性检查的商品控制器
 * @author Ming Qiu
 * @date Created in 2021-6-13 13:22
 **/
@RestController /*Restful的Controller对象*/
@RequestMapping(value = "/codevalid/goods", produces = "application/json;charset=UTF-8")
public class CodeValidGoodsController {

    private final Logger logger = LoggerFactory.getLogger(CodeValidGoodsController.class);


    @Autowired
    private GoodsService goodsService;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @PostMapping("")
    public Object createGood(@RequestBody GoodsVo goodsVo){
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        if (null == goodsVo.getName()){
            logger.info("商品名称不能为空");
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ReturnObject retObj = new ReturnObject();
            retObj.setErrmsg("商品名称不能为空");
            retObj.setErrno(ResponseCode.OK);
            return retObj;
        }

        Goods new_goods = goodsService.createGoods(goodsVo);
        httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        return ResponseUtil.ok(new_goods);
    }
}
