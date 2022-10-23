package cn.edu.xmu.javaee.goodsdemo.controller;

import cn.edu.xmu.javaee.core.util.ReturnNo;
import cn.edu.xmu.javaee.core.util.ReturnObject;
import cn.edu.xmu.javaee.goodsdemo.controller.vo.ProductRetVo;
import cn.edu.xmu.javaee.goodsdemo.controller.vo.ProductVo;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.Product;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.User;
import cn.edu.xmu.javaee.goodsdemo.service.ProductService;
import cn.edu.xmu.javaee.core.util.BusinessException;
import cn.edu.xmu.javaee.core.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static cn.edu.xmu.javaee.core.util.ResponseUtil.retWithObj;

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
    public ReturnObject getProductById(@PathVariable("id") Long id) {
        logger.debug("getProductById: id = {} " ,id);
        Product product = null;
        product = productService.retrieveProductByID(id, false);
        ProductRetVo productRetVo = new ProductRetVo(product);
        return retWithObj(productRetVo);
    }

    @GetMapping("")
    public Object searchProductByName(@RequestParam String name) {
        Object retObj = null;
        List<Product> productList = null;
        productList = productService.retrieveProductByName(name, false);
        if (null != productList) {
            List<ProductRetVo> data = new ArrayList<>(productList.size());
            for (Product bo : productList) {
                data.add(new ProductRetVo(bo));
            }
            retObj = retWithObj(data);
        } else {
            retObj = retWithObj(null);
        }
        return  retObj;
    }


    @PostMapping("")
    public Object createProduct(@Validated @RequestBody ProductVo productVo){
        Product product = productVo.createBo();
        User user = new User(Long.valueOf(1), "admin1");
        Product retProduct = productService.createProduct(product, user);
        ProductVo vo = new ProductVo(retProduct);
        return retWithObj(ReturnNo.CREATED, ReturnNo.CREATED.getMessage(), vo);
    }

    @PutMapping("{id}")
    public Object modiProduct(@PathVariable Long id, @RequestBody ProductVo productVo){
        User user = new User(Long.valueOf(2), "admin2");
        Product product = productVo.createBo();
        product.setId(id);
        productService.modifyProduct(product, user);
        return retWithObj(null);
    }

    @DeleteMapping("{id}")
    public Object delProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return retWithObj(null);
    }

}
