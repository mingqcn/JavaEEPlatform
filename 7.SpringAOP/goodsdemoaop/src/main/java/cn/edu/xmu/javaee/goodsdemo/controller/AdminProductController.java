package cn.edu.xmu.javaee.goodsdemo.controller;

import cn.edu.xmu.javaee.core.aop.Audit;
import cn.edu.xmu.javaee.core.aop.LoginName;
import cn.edu.xmu.javaee.core.aop.LoginUser;
import cn.edu.xmu.javaee.core.model.PageObj;
import cn.edu.xmu.javaee.core.util.ReturnNo;
import cn.edu.xmu.javaee.core.util.ReturnObject;
import cn.edu.xmu.javaee.goodsdemo.controller.vo.ProductVo;
import cn.edu.xmu.javaee.core.model.SimpleUser;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.Product;
import cn.edu.xmu.javaee.goodsdemo.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.xmu.javaee.core.util.Common.cloneObj;
import static cn.edu.xmu.javaee.core.util.Common.createPageObj;

/**
 * 商品控制器
 * @author Ming Qiu
 */
@RestController /*Restful的Controller对象*/
@RequestMapping(value = "/admin/products", produces = "application/json;charset=UTF-8")
public class AdminProductController{

    private final Logger logger = LoggerFactory.getLogger(AdminProductController.class);


    private ProductService productService;

    @Autowired
    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    @Audit
    public ReturnObject getProductById(@PathVariable("id") Long id) {
        logger.debug("getProductById: id = {} " ,id);
        Product product = null;
        product = productService.retrieveProductByID(id, false);
        ProductVo productVo = cloneObj(product, ProductVo.class);
        logger.debug("getProductById: productVo = {} ", productVo );
        return new ReturnObject(productVo);
    }

    @GetMapping("")
    @Audit
    public ReturnObject searchProductByName(@RequestParam String name,
                                            @RequestParam(required = false,defaultValue = "1") Integer page,
                                            @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        PageInfo<Product> productList = productService.retrieveProductByName(name, false, page, pageSize);
        if (null != productList) {
            PageObj<ProductVo> data = createPageObj(productList, ProductVo.class);
            return new ReturnObject(data);
        } else {
            return new ReturnObject();
        }
    }


    @PostMapping("")
    @Audit
    public ReturnObject createProduct(@Validated @RequestBody ProductVo productVo, @LoginUser Long userId, @LoginName String userName){
        Product product = cloneObj(productVo, Product.class);
        SimpleUser user = new SimpleUser(userId, userName);
        Product retProduct = productService.createProduct(product, user);
        ProductVo vo = cloneObj(retProduct, ProductVo.class);
        return new ReturnObject(ReturnNo.CREATED, vo);
    }

    @PutMapping("{id}")
    @Audit
    public ReturnObject modiProduct(@PathVariable Long id, @RequestBody ProductVo productVo, @LoginUser Long userId, @LoginName String userName){
        SimpleUser user = new SimpleUser(userId, userName);
        Product product = cloneObj(productVo, Product.class);
        product.setId(id);
        productService.modifyProduct(product, user);
        return new ReturnObject();
    }

    @DeleteMapping("{id}")
    @Audit
    public ReturnObject delProduct(@PathVariable("id") Long id, @LoginUser Long userId, @LoginName String userName) {
        SimpleUser user = new SimpleUser(userId, userName);
        productService.deleteProduct(id, user);
        return new ReturnObject();
    }

}
