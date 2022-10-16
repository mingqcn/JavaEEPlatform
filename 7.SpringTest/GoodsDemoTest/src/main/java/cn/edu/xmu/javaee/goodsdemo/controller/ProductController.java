package cn.edu.xmu.javaee.goodsdemo.controller;

import cn.edu.xmu.javaee.goodsdemo.controller.vo.ProductRetVo;
import cn.edu.xmu.javaee.goodsdemo.controller.vo.ProductVo;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.Product;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.User;
import cn.edu.xmu.javaee.goodsdemo.service.ProductService;
import cn.edu.xmu.javaee.goodsdemo.util.BusinessException;
import cn.edu.xmu.javaee.goodsdemo.util.ResponseUtil;
import cn.edu.xmu.javaee.goodsdemo.util.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static cn.edu.xmu.javaee.goodsdemo.util.Common.returnWithStatus;

/**
 * 商品控制器
 * @author Ming Qiu
 */
@RestController /*Restful的Controller对象*/
@RequestMapping(value = "/customer/products", produces = "application/json;charset=UTF-8")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public Object getProductById(@PathVariable("id") Long id, @RequestParam(required = false, defaultValue = "auto") String type) {
        logger.debug("getProductById: id = {} " ,id);
        Object retObj = null;
        Product product = null;
        try {
            if (null != type && "manual" == type){
                product = productService.findProductById_manual(id);
            } else {
                product = productService.retrieveProductByID(id, true);
            }
            ProductRetVo productRetVo = new ProductRetVo(product);
            retObj = ResponseUtil.ok(productRetVo);
        }
        catch (BusinessException e){
            retObj = returnWithStatus(null, e);
        }
        return  retObj;
    }



    @GetMapping("")
    public Object searchProductByName(@RequestParam String name, @RequestParam(required = false, defaultValue = "auto") String type) {
        Object retObj = null;
        try{
            List<Product> productList = null;
            if (null != type && "manual" == type){
                productList = productService.findProductByName_manual(name);
            } else {
                productList = productService.retrieveProductByName(name, true);
            }
            if (null != productList) {
                List<ProductRetVo> data = new ArrayList<>(productList.size());
                for (Product bo : productList) {
                    data.add(new ProductRetVo(bo));
                }
                retObj = ResponseUtil.ok(data);
            } else {
                retObj = ResponseUtil.ok();
            }
        }
        catch (BusinessException e){
            retObj = returnWithStatus(null, e);
        }
        return  retObj;
    }
}
