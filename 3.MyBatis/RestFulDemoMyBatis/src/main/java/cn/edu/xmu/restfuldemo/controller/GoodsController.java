package cn.edu.xmu.restfuldemo.controller;

import cn.edu.xmu.restfuldemo.model.GoodsRetVo;
import cn.edu.xmu.restfuldemo.model.GoodsVo;
import cn.edu.xmu.restfuldemo.model.VoObject;
import cn.edu.xmu.restfuldemo.util.ResponseCode;
import cn.edu.xmu.restfuldemo.util.ReturnObject;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import cn.edu.xmu.restfuldemo.service.GoodsService;
import cn.edu.xmu.restfuldemo.util.ResponseUtil;

import javax.servlet.http.HttpServletResponse;

import static cn.edu.xmu.restfuldemo.util.Common.*;
/**
 * 商品控制器
 * @author Ming Qiu
 */
@Api(value = "商品API", tags = "商品API")
@RestController /*Restful的Controller对象*/
@RequestMapping(value = "/goods", produces = "application/json;charset=UTF-8")
public class GoodsController {

    private final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @ApiOperation(value = "获得一个商品对象",  produces="application/json;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "Integer", name = "id", value ="商品对象id" ,required = true)
    })
    @ApiResponses({
    })
    @GetMapping("{id}")
    public Object getGoodsById(@PathVariable("id") Integer id) {
        ReturnObject<VoObject> returnObject =  goodsService.findById(id);
        ResponseCode code = returnObject.getCode();
        switch (code){
            case RESOURCE_ID_NOTEXIST:
                httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
                return ResponseUtil.fail(returnObject.getCode(), returnObject.getErrmsg());
            case OK:
                GoodsRetVo goodsRetVo = (GoodsRetVo) returnObject.getData().createVo();
                return ResponseUtil.ok(goodsRetVo);
            default:
                return ResponseUtil.fail(code);
        }
    }



    @ApiOperation(value = "用商品名称搜索",  produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "name", value ="商品名称", required = true)
    })
    @ApiResponses({
    })
    @GetMapping("search")
    public Object searchGoodsByName(@RequestParam String name) {
        ReturnObject<VoObject> returnObject = goodsService.searchByName(name);
        return getRetObject(returnObject);
    }

    @ApiOperation(value = "新建商品",  produces="application/json")
    @ApiImplicitParams({
    })
    @ApiResponses({
    })
    @PostMapping("")
    public Object createGood(@Validated @RequestBody GoodsVo goodsVo, BindingResult bindingResult){

        Object returnObject = processFieldErrors(bindingResult, httpServletResponse);
        if (null != returnObject){
            return returnObject;
        }
        ReturnObject<VoObject> returnObject1 = goodsService.createGoods(goodsVo);
        httpServletResponse.setStatus(HttpStatus.CREATED.value());
        return getRetObject(returnObject1);
    }

    @ApiOperation(value = "修改商品",  produces="application/json")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
    })
    @PutMapping("{id}")
    public Object modiGood(@PathVariable Integer id, @RequestBody GoodsVo goodsVo){

        ReturnObject<Object> returnObject = goodsService.modifyGoods(id, goodsVo, 1);
        return getNullRetObj(returnObject, httpServletResponse);
    }

    @ApiOperation(value = "删除商品",  produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "Integer", name = "id", value ="商品对象id" ,required = true)
    })
    @ApiResponses({
    })
    @DeleteMapping("{id}")
    public Object delGoods(@PathVariable("id") Integer id) {
        ReturnObject<Object> returnObject = goodsService.delGoods(id);
        return getNullRetObj(returnObject, httpServletResponse);
    }

}
