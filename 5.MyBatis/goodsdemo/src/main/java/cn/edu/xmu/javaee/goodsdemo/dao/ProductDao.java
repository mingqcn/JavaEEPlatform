//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.dao;

import cn.edu.xmu.javaee.goodsdemo.dao.bo.OnSale;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.Product;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.User;
import cn.edu.xmu.javaee.goodsdemo.mapper.generator.ProductPoMapper;
import cn.edu.xmu.javaee.goodsdemo.mapper.po.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.javaee.goodsdemo.util.BusinessException;
import cn.edu.xmu.javaee.goodsdemo.util.ReturnNo;

/**
 * @author Ming Qiu
 **/
@Repository
public class ProductDao {

    private Logger logger = LoggerFactory.getLogger(ProductDao.class);

    private ProductPoMapper productPoMapper;

    private OnSaleDao onSaleDao;

    @Autowired
    public ProductDao(ProductPoMapper productPoMapper, OnSaleDao onSaleDao) {
        this.productPoMapper = productPoMapper;
        this.onSaleDao = onSaleDao;
    }

    /**
     * 用GoodsPo对象找Goods对象
     * @param name
     * @return  Goods对象列表，带关联的Product返回
     */
    public List<Product> findProductByName(String name) throws BusinessException {
        List<Product> productList = new ArrayList<>();
        ProductPoExample example = new ProductPoExample();
        ProductPoExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        try{
            List<ProductPo> productPoList = productPoMapper.selectByExample(example);
            for (ProductPo po : productPoList){
                Product product = getProductWithOnSale(po);
                productList.add(product);
            }
            logger.info("findProductByName: productList =" + productList);
        }
        catch(DataAccessException e){
            logger.error(e.getMessage());
            throw new BusinessException(ReturnNo.INTERNAL_SERVER_ERR, "数据库访问错误");
        }

        return productList;
    }

    /**
     * 用GoodsPo对象找Goods对象
     * @param  productId
     * @return  Goods对象列表，带关联的Product返回
     */
    public Product findProductByID(Long productId) throws BusinessException {
        Product product = null;
        try{
            ProductPo productPo = productPoMapper.selectByPrimaryKey(productId);
            if (null == productPo){
                throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST, "产品id不存在");
            }
            product = getProductWithOnSale(productPo);
            logger.info("findProductByID: product =" + product);
        }
        catch(DataAccessException e){
            logger.error(e.getMessage());
            throw new BusinessException(ReturnNo.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
        return product;
    }

    private Product getProductWithOnSale(ProductPo productPo) throws DataAccessException{
        assert productPo != null;
        Product product =  new Product(productPo);

        OnSalePo latestOnSalePo = onSaleDao.getLatestOnSale(productPo.getId());
        if (null != latestOnSalePo){
            OnSale onsale = new OnSale(latestOnSalePo);
            product.getOnSaleList().add(onsale);
        }

        List<ProductPo> otherPo = getOtherProduct(productPo);
        for (ProductPo po : otherPo){
            product.getOtherProduct().add(new Product(po));
        }

        return product;
    }

    private List<ProductPo> getOtherProduct(ProductPo productPo) throws DataAccessException{
        assert productPo != null;

        ProductPoExample example = new ProductPoExample();
        ProductPoExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(productPo.getGoodsId());
        criteria.andIdNotEqualTo(productPo.getId());
        List<ProductPo> productPoList = productPoMapper.selectByExample(example);

        return productPoList;
    }

    /**
     * 创建Goods对象
     * @param product 传入的Goods对象
     * @return 返回对象ReturnObj
     */
    public Product createProduct(Product product, User user) throws BusinessException{

        Product retObj = null;
        try{
            ProductPo po = product.getPo();
            po.setGmtCreate(LocalDateTime.now());
            po.setCreatorId(user.getId());
            po.setCreatorName(user.getName());
            int ret = productPoMapper.insertSelective(po);
            retObj = new Product(po);
        }
        catch(DataAccessException e){
            logger.error(e.getMessage());
            throw new BusinessException(ReturnNo.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
        return retObj;
    }

    /**
     * 修改商品信息
     * @param product 传入的product对象
     * @return void
     */
    public void modiProduct(Product product, User user) throws BusinessException{
        try{
            ProductPo po = product.getPo();
            po.setGmtModified(LocalDateTime.now());
            po.setModifierId(user.getId());
            po.setModifierName(user.getName());
            int ret = productPoMapper.updateByPrimaryKeySelective(po);
            if (ret == 0 ){
                throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST);
            }
        }
        catch(DataAccessException e){
            logger.error(e.getMessage());
            throw new BusinessException(ReturnNo.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
    }

    /**
     * 删除商品，连带规格
     * @param id 商品id
     * @return
     */
    public void deleteProduct(Long id) throws BusinessException{
        try{
            int ret = productPoMapper.deleteByPrimaryKey(id);
            if (ret == 0) {
                throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST);
            }
        }
        catch(DataAccessException e){
            logger.error(e.getMessage());
            throw new BusinessException(ReturnNo.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
    }
}
