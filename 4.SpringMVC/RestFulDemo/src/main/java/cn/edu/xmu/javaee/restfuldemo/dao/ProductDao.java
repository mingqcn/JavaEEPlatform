package cn.edu.xmu.javaee.restfuldemo.dao;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.restfuldemo.dao.bo.ProductDraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

    private final Logger logger = LoggerFactory.getLogger(ProductDao.class);

    public ProductDraft insert(ProductDraft draft){
        draft.setId(1L);
        return draft;
    }

    public ProductDraft findById(Long shopId, Long id) throws BusinessException{
        if (1L != id) {
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST, String.format(ReturnNo.RESOURCE_ID_NOTEXIST.getMessage(),"产品申请", id));
        } else{
            if (2L != shopId){
                throw new BusinessException(ReturnNo.RESOURCE_ID_OUTSCOPE, String.format(ReturnNo.RESOURCE_ID_OUTSCOPE.getMessage(),"产品申请", id, shopId));
            }else {
                return ProductDraft.builder().id(1L).name("测试1").originalPrice(100L).originPlace("厦门").categoryId(2L).shopId(2L).productId(100L).build();
            }
        }
    }

    public void deleteById(Long id){
        return;
    }

    public void updateById(ProductDraft draft){
        return;
    }
}
