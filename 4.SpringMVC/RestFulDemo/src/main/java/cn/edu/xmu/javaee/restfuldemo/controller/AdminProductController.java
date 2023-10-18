//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.restfuldemo.controller;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.validation.UpdateGroup;
import cn.edu.xmu.javaee.restfuldemo.controller.dto.SimpleProductDraftDto;
import cn.edu.xmu.javaee.restfuldemo.controller.vo.ProductDraftVo;
import cn.edu.xmu.javaee.restfuldemo.dao.bo.ProductDraft;
import cn.edu.xmu.javaee.restfuldemo.service.ProductService;
import cn.edu.xmu.javaee.core.validation.NewGroup;
import cn.edu.xmu.javaee.core.model.ReturnObject;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品控制器
 * @author Ming Qiu
 */
@RestController
@RequestMapping(value = "/shops/{shopId}", produces = "application/json;charset=UTF-8")
public class AdminProductController {

    private final Logger logger = LoggerFactory.getLogger(AdminProductController.class);

    private ProductService productService;

    @Autowired
    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 商铺管理员申请增加新的Product
     * @param shopId
     * @return
     */
    @PostMapping("/draftproducts")
    @ResponseStatus(HttpStatus.CREATED)
    public ReturnObject createDraft(@PathVariable Long shopId,
                                    @RequestBody @Validated({NewGroup.class}) ProductDraftVo vo){
        logger.debug("createDraft: vo = {}",vo);
        ProductDraft newDraft = ProductDraft.builder().name(vo.getName()).originalPrice(vo.getOriginalPrice()).originPlace(vo.getOriginPlace()).categoryId(vo.getCategoryId()).build();
        newDraft.setShopId(shopId);
        ProductDraft draft = this.productService.createDraft(newDraft);
        SimpleProductDraftDto draftDto = SimpleProductDraftDto.builder().id(draft.getId()).name(draft.getName()).originalPrice(draft.getOriginalPrice()).originPlace(draft.getOriginPlace()).build();
        return new ReturnObject(ReturnNo.CREATED, ReturnNo.CREATED.getMessage(), draftDto);
    }

    /**
     * 管理员或店家物理删除审核中的Products
     * @author wuzhicheng
     * @param shopId
     * @param id
     * @return
     */
    @GetMapping("/draftproducts/{id}")
    public ReturnObject getProducts(@PathVariable Long shopId, @PathVariable Long id, HttpServletResponse response){
        logger.debug("getProducts: shopId = {}, id = {}",shopId, id);
        ProductDraft draft;
        try {
            draft = this.productService.getDraft(shopId, id);
        }catch (BusinessException e){
            if (e.getErrno() == ReturnNo.RESOURCE_ID_NOTEXIST){
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }else if (e.getErrno() == ReturnNo.RESOURCE_ID_OUTSCOPE){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
            return new ReturnObject(e.getErrno(), e.getMessage());
        }
        SimpleProductDraftDto draftDto = SimpleProductDraftDto.builder().id(draft.getId()).name(draft.getName()).originalPrice(draft.getOriginalPrice()).originPlace(draft.getOriginPlace()).build();
        return new ReturnObject(draftDto);
    }
    /**
     * 管理员或店家物理删除审核中的Products
     * @author wuzhicheng
     * @param shopId
     * @param id
     * @return
     */
    @DeleteMapping("/draftproducts/{id}")
    public ReturnObject delProducts(@PathVariable Long shopId, @PathVariable Long id, HttpServletResponse response){
        try {
            this.productService.delDraft(shopId, id);
        }catch (BusinessException e){
            if (e.getErrno() == ReturnNo.RESOURCE_ID_NOTEXIST){
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }else if (e.getErrno() == ReturnNo.RESOURCE_ID_OUTSCOPE){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
            return new ReturnObject(e.getErrno(), e.getMessage());
        }
        return new ReturnObject();
    }

    /**
     * 管理员或店家修改审核中的Products
     * @author wuzhicheng
     * @param shopId
     * @param id
     * @param vo
     * @return
     */
    @PutMapping("/draftproducts/{id}")
    public ReturnObject modifyDraft(@PathVariable Long shopId, @PathVariable Long id,@RequestBody @Validated({UpdateGroup.class}) ProductDraftVo vo, HttpServletResponse response){
        ProductDraft updateDraft = ProductDraft.builder().name(vo.getName()).originalPrice(vo.getOriginalPrice()).originPlace(vo.getOriginPlace()).categoryId(vo.getCategoryId()).build();
        updateDraft.setId(id);
        try {
            this.productService.updateDraft(shopId, updateDraft);
        }catch (BusinessException e){
            if (e.getErrno() == ReturnNo.RESOURCE_ID_NOTEXIST){
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }else if (e.getErrno() == ReturnNo.RESOURCE_ID_OUTSCOPE){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
            return new ReturnObject(e.getErrno(), e.getMessage());
        }
        return new ReturnObject();
    }
}
