package cn.edu.xmu.javaee.goodsdemo.controller;

import cn.edu.xmu.javaee.core.model.PageObj;
import cn.edu.xmu.javaee.core.util.ReturnNo;
import cn.edu.xmu.javaee.core.util.ReturnObject;
import cn.edu.xmu.javaee.goodsdemo.controller.vo.ProductRetVo;
import cn.edu.xmu.javaee.goodsdemo.controller.vo.ProductVo;
import cn.edu.xmu.javaee.goodsdemo.controller.vo.UserVo;
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
        ProductRetVo productRetVo = cloneObj(product, ProductRetVo.class);
        return new ReturnObject(productRetVo);
    }

    @GetMapping("")
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
    public ReturnObject createProduct(@Validated @RequestBody ProductVo productVo){
        Product product = cloneObj(productVo, Product.class);
        UserVo userVo = new UserVo(Long.valueOf(1), "admin1");
        Product retProduct = productService.createProduct(product, userVo);
        ProductVo vo = cloneObj(retProduct, ProductVo.class);
        return new ReturnObject(ReturnNo.CREATED, vo);
    }

    @PutMapping("{id}")
    public ReturnObject modiProduct(@PathVariable Long id, @RequestBody ProductVo productVo){
        UserVo userVo = new UserVo(Long.valueOf(2), "admin2");
        Product product = cloneObj(productVo, Product.class);
        product.setId(id);
        productService.modifyProduct(product, userVo);
        return new ReturnObject();
    }

    @DeleteMapping("{id}")
    public ReturnObject delProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ReturnObject();
    }

}
