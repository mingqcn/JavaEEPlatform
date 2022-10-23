package cn.edu.xmu.javaee.goodsdemo.controller;

import cn.edu.xmu.javaee.goodsdemo.controller.vo.ProductRetVo;
import cn.edu.xmu.javaee.goodsdemo.controller.vo.ProductVo;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.Product;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.User;
import cn.edu.xmu.javaee.goodsdemo.service.ProductService;
import cn.edu.xmu.javaee.goodsdemo.util.BusinessException;
import cn.edu.xmu.javaee.goodsdemo.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static cn.edu.xmu.javaee.goodsdemo.util.Common.returnWithStatus;

/**
 * 商品控制器
 * @author Ming Qiu
 */
@RestController /*Restful的Controller对象*/
@RequestMapping(value = "/admin/products", produces = "application/json;charset=UTF-8")
public class AdminProductController {

    private final Logger logger = LoggerFactory.getLogger(AdminProductController.class);


    private ProductService productService;

    @Autowired
    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public Object getProductById(@PathVariable("id") Long id) {
        logger.debug("getProductById: id = {} " ,id);
        Object retObj = null;
        Product product = null;
        try {

            product = productService.retrieveProductByID(id, false);
            ProductRetVo productRetVo = new ProductRetVo(product);
            retObj = ResponseUtil.ok(productRetVo);
        }
        catch (BusinessException e){
            retObj = returnWithStatus(null, e);
        }
        return  retObj;
    }



    @GetMapping("")
    public Object searchProductByName(@RequestParam String name) {
        Object retObj = null;
        try{
            List<Product> productList = null;
            productList = productService.retrieveProductByName(name, false);
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


    @PostMapping("")
    public Object createProduct(@RequestBody ProductVo productVo){
        Object retObj = null;
        try{
            Product product = productVo.createBo();
            User user = new User(Long.valueOf(1), "admin1");
            Product retProduct = productService.createProduct(product, user);
            ProductVo vo = new ProductVo(retProduct);
            retObj = new ResponseEntity(
                    ResponseUtil.ok(vo),
                    HttpStatus.CREATED);
        }
        catch (BusinessException e){
            retObj = returnWithStatus(null, e);
        }
        return  retObj;
    }

    @PutMapping("{id}")
    public Object modiProduct(@PathVariable Long id, @RequestBody ProductVo productVo){
        Object retObj = null;
        try{
            User user = new User(Long.valueOf(2), "admin2");
            Product product = productVo.createBo();
            product.setId(id);
            productService.modifyProduct(product, user);
            retObj = ResponseUtil.ok();
        }
        catch (BusinessException e){
            retObj = returnWithStatus(null, e);
        }
        return  retObj;

    }

    @DeleteMapping("{id}")
    public Object delProduct(@PathVariable("id") Long id) {
        Object retObj = null;
        try{
            productService.deleteProduct(id);
            retObj = ResponseUtil.ok();
        }
        catch (BusinessException e){
            retObj = returnWithStatus(null, e);
        }
        return  retObj;
    }

}
