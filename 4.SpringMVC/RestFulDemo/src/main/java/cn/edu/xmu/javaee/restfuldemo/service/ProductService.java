package cn.edu.xmu.javaee.restfuldemo.service;


import cn.edu.xmu.javaee.restfuldemo.dao.ProductDao;
import cn.edu.xmu.javaee.restfuldemo.dao.bo.ProductDraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    final private Logger logger = LoggerFactory.getLogger(ProductService.class);

    private ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductDraft createDraft(ProductDraft draft){
        return this.productDao.insert(draft);
    }

    public ProductDraft getDraft(Long shopId, Long draftId){
        ProductDraft draft = this.productDao.findById(shopId, draftId);
        logger.debug("getDraft: draft = {}", draft);
        return draft;
    }

    public void delDraft(Long shopId, Long draftId){
        this.productDao.findById(shopId, draftId);
        this.productDao.deleteById(draftId);
    }

    public void updateDraft(Long shopId, ProductDraft draft){
        this.productDao.findById(shopId, draft.getId());
        this.productDao.updateById(draft);
    }
}
