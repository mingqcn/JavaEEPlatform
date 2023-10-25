package cn.edu.xmu.javaee.productdemoaop.controller;

import cn.edu.xmu.javaee.core.aop.LoginUser;
import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.model.ReturnObject;
import cn.edu.xmu.javaee.core.validation.NewGroup;
import cn.edu.xmu.javaee.core.validation.UpdateGroup;
import cn.edu.xmu.javaee.productdemoaop.controller.dto.AdminProductDto;
import cn.edu.xmu.javaee.productdemoaop.controller.dto.ProductDto;
import cn.edu.xmu.javaee.productdemoaop.controller.vo.ProductVo;
import cn.edu.xmu.javaee.productdemoaop.dao.bo.Product;
import cn.edu.xmu.javaee.productdemoaop.dao.bo.User;
import cn.edu.xmu.javaee.productdemoaop.service.ProductService;
import cn.edu.xmu.javaee.productdemoaop.util.CloneFactory;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 商品控制器
 * @author Ming Qiu
 */
@RestController /*Restful的Controller对象*/
@RequestMapping(value = "/admin/products", produces = "application/json;charset=UTF-8")
public class AdminProductController {

    private final static Logger logger = LoggerFactory.getLogger(AdminProductController.class);


    private ProductService productService;

    @Autowired
    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ReturnObject getProductById(@PathVariable("id") Long id) {
        logger.debug("getProductById: id = {} " ,id);
        ReturnObject retObj = null;
        Product product = productService.retrieveProductByID(id, false);
        AdminProductDto productDto = CloneFactory.copy(new AdminProductDto(), product);
        retObj = new ReturnObject(productDto);
        return  retObj;
    }



    @GetMapping("")
    public ReturnObject searchProductByName(@RequestParam String name) {
        ReturnObject retObj = null;
        List<Product> productList = null;
        productList = productService.retrieveProductByName(name, false);
        List<AdminProductDto> data = productList.stream().map(o->CloneFactory.copy(new AdminProductDto(), o)).collect(Collectors.toList());
        retObj = new ReturnObject(data);
        return  retObj;
    }


    @PostMapping("")
    public ReturnObject createProduct(@RequestBody @Validated(NewGroup.class) ProductVo productVo, @LoginUser User user){
        ReturnObject retObj = null;
        Product product = CloneFactory.copy(new Product(), productVo);
        Product retProduct = productService.createProduct(product, user);
        ProductDto dto = CloneFactory.copy(new ProductDto(), retProduct);
        retObj = new ReturnObject(ReturnNo.CREATED, dto);
        return  retObj;
    }

    @PutMapping("{id}")
    public ReturnObject modiProduct(@PathVariable Long id, @RequestBody @Validated(UpdateGroup.class) ProductVo productVo, @LoginUser User user){
        ReturnObject retObj = null;
        Product product = CloneFactory.copy(new Product(), productVo);;
        product.setId(id);
        productService.modifyProduct(product, user);
        retObj = new ReturnObject();
        return  retObj;

    }

    @DeleteMapping("{id}")
    public ReturnObject delProduct(@PathVariable("id") Long id, HttpServletResponse response) {
        ReturnObject retObj = null;
        productService.deleteProduct(id);
        retObj = new ReturnObject();
        return  retObj;
    }

}
