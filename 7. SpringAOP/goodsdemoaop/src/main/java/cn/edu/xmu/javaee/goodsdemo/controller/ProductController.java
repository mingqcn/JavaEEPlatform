package cn.edu.xmu.javaee.goodsdemo.controller;

import cn.edu.xmu.javaee.core.model.PageObj;
import cn.edu.xmu.javaee.core.util.JacksonUtil;
import cn.edu.xmu.javaee.core.util.ReturnObject;
import cn.edu.xmu.javaee.goodsdemo.controller.vo.ProductRetVo;
import cn.edu.xmu.javaee.goodsdemo.controller.vo.SimpleProductRetVo;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.Product;
import cn.edu.xmu.javaee.goodsdemo.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static cn.edu.xmu.javaee.core.util.Common.cloneObj;
import static cn.edu.xmu.javaee.core.util.Common.createListObj;

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
    public ReturnObject findProductById(@PathVariable("id") Long id,
                                        @RequestParam(required = false, defaultValue = "auto") String type) {
        Product product = null;
        if (null != type && "manual" == type){
            product = productService.findProductById_manual(id);
        } else {
            product = productService.retrieveProductByID(id, true);
        }
        ProductRetVo productRetVo = cloneObj(product, ProductRetVo.class);
        List<ProductRetVo> otherProduct = createListObj(product.getOtherProduct(), ProductRetVo.class);
        productRetVo.setOtherProduct(createListObj(otherProduct, SimpleProductRetVo.class));
        productRetVo.setValidOnSale(product.getValidOnSale());
        ReturnObject retObj = new ReturnObject(productRetVo);
        logger.debug("findProductById: retObj = {} " , JacksonUtil.toJson(retObj));
        return retObj;
    }

    @GetMapping("")
    public ReturnObject searchProductByName(@RequestParam String name,
                                            @RequestParam(required = false, defaultValue = "auto") String type,
                                            @RequestParam(required = false,defaultValue = "1") Integer page,
                                            @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        PageInfo<Product> productList = null;
        if (null != type && "manual" == type){
            productList = productService.findProductByName_manual(name, page, pageSize);
        } else {
            productList = productService.retrieveProductByName(name, true, page, pageSize);
        }

        List<ProductRetVo> voObjs = new ArrayList<>(productList.getList().size());
        for (Product item : productList.getList()) {
            ProductRetVo  vo = cloneObj(item, ProductRetVo.class);
            logger.debug("searchProductByName: item = {}", item);
            vo.setOtherProduct(createListObj(item.getOtherProduct(), SimpleProductRetVo.class));
            voObjs.add(vo);
            logger.debug("searchProductByName: voObjs = {}", voObjs);
        }

        PageObj<ProductRetVo> data = new PageObj(voObjs, productList.getTotal(), productList.getPageNum(),productList.getPageSize(),productList.getPages());
        return new ReturnObject(data);
    }
}
