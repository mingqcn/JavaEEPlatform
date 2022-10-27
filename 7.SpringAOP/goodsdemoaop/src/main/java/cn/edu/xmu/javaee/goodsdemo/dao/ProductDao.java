//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.dao;

import cn.edu.xmu.javaee.core.model.SimpleUser;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.OnSale;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.Product;
import cn.edu.xmu.javaee.goodsdemo.mapper.generator.ProductPoMapper;
import cn.edu.xmu.javaee.goodsdemo.mapper.generator.po.ProductPo;
import cn.edu.xmu.javaee.goodsdemo.mapper.generator.po.ProductPoExample;
import cn.edu.xmu.javaee.goodsdemo.mapper.manual.ProductAllMapper;
import cn.edu.xmu.javaee.goodsdemo.mapper.manual.po.ProductAllPo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.javaee.core.util.BusinessException;
import cn.edu.xmu.javaee.core.util.ReturnNo;

import static cn.edu.xmu.javaee.core.util.Common.*;

/**
 * @author Ming Qiu
 **/
@Repository
public class ProductDao {

    @Value("${goodsdemo.product.max-related-product}")
    private int max_related_product;

    private Logger logger = LoggerFactory.getLogger(ProductDao.class);

    private ProductPoMapper productPoMapper;

    private OnSaleDao onSaleDao;

    private ProductAllMapper productAllMapper;

    @Autowired
    public ProductDao(ProductPoMapper productPoMapper, OnSaleDao onSaleDao, ProductAllMapper productAllMapper) {
        this.productPoMapper = productPoMapper;
        this.onSaleDao = onSaleDao;
        this.productAllMapper = productAllMapper;
    }

    /**
     * 用GoodsPo对象找Goods对象
     * @param name
     * @return  Goods对象列表，带关联的Product返回
     */
    public PageInfo<Product> retrieveProductByName(String name, boolean all, int page, int pageSize) throws BusinessException {
        List<Product> productList = new ArrayList<>();
        ProductPoExample example = new ProductPoExample();
        ProductPoExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        PageHelper.startPage(page, pageSize, false);
        try{
            List<ProductPo> productPoList = productPoMapper.selectByExample(example);
            for (ProductPo po : productPoList){
                Product product = null;
                if (all) {
                    product = this.retrieveFullProduct(po);
                } else {
                    product = cloneObj(po, Product.class);
                }
                productList.add(product);
            }
            logger.debug("retrieveProductByName: productList = {}", productList);

        }
        catch(DataAccessException e){
            logger.error(e.getMessage());
            throw new BusinessException(ReturnNo.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
        return new PageInfo<>(productList);
    }

    /**
     * 用GoodsPo对象找Goods对象
     * @param  productId
     * @return  Goods对象列表，带关联的Product返回
     */
    public Product retrieveProductByID(Long productId, boolean all) throws BusinessException {
        Product product = null;
        try{
            ProductPo productPo = productPoMapper.selectByPrimaryKey(productId);
            logger.debug("retrieveProductByID : productPo = {}", productPo);
            if (null == productPo){
                throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST, "产品id不存在");
            }
            if (all) {
                product = this.retrieveFullProduct(productPo);
            } else {
                product = cloneObj(productPo, Product.class);
            }

            logger.debug("retrieveProductByID: product = {}",  product);
        }
        catch(DataAccessException e){
            logger.error(e.getMessage());
            throw new BusinessException(ReturnNo.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
        return product;
    }


    private Product retrieveFullProduct(ProductPo productPo) throws DataAccessException{
        assert productPo != null;
        Product product =  cloneObj(productPo, Product.class);
        List<OnSale> latestOnSale = onSaleDao.getLatestValidOnSale(productPo.getId());
        logger.debug("retrieveFullProduct: latestOnSale = {}", latestOnSale);

        product.setOnSaleList(latestOnSale);
        logger.debug("retrieveFullProduct: product.onSaleList = {}", product.getOnSaleList());
        List<Product> otherProduct = retrieveOtherProduct(productPo);
        logger.debug("retrieveFullProduct: otherProduct = {}", otherProduct);
        product.addOtherProduct(otherProduct);
        logger.debug("retrieveFullProduct: product.getValidOnSale = {}", product.getValidOnSale());
        return product;
    }

    private List<Product> retrieveOtherProduct(ProductPo productPo) throws DataAccessException{
        assert productPo != null;

        ProductPoExample example = new ProductPoExample();
        PageHelper.startPage(1, max_related_product);
        ProductPoExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(productPo.getGoodsId());
        criteria.andIdNotEqualTo(productPo.getId());
        List<ProductPo> productPoList = productPoMapper.selectByExample(example);
        return createListObj(productPoList, Product.class);
    }

    /**
     * 创建Goods对象
     * @param product 传入的Goods对象
     * @return 返回对象ReturnObj
     */
    public Product createProduct(Product product, SimpleUser user) throws BusinessException{

        Product retObj = null;
        try{
            ProductPo po = cloneObj(product,ProductPo.class);
            putPoCreatedFields(po, user);
            int ret = productPoMapper.insertSelective(po);
            retObj = cloneObj(po,Product.class);
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
    public void modiProduct(Product product, SimpleUser user) throws BusinessException{
        try{
            ProductPo po = cloneObj(product,ProductPo.class);
            putPoModifiedFields(po, user);
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
    public void deleteProduct(Long id, SimpleUser user) throws BusinessException{
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

    public PageInfo<Product> findProductByName_manual(String name, int page, int pageSize) throws BusinessException {
        List<Product> productList = null;
        ProductPoExample example = new ProductPoExample();
        ProductPoExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        PageHelper.startPage(page, pageSize, false);
        try{
            List<ProductAllPo> productPoList = productAllMapper.getProductWithAll(example);
            productList = new ArrayList<>(productPoList.size());
            for (ProductAllPo po : productPoList){
                Product product = cloneObj(po, Product.class);
                product.addOtherProduct(createListObj(po.getOtherProduct(), Product.class));
                product.setOnSaleList(createListObj(po.getOnSaleList(), OnSale.class));
                productList.add(product);
            }
            logger.debug("findProductByName_manual: productList = {}", productList);
        }
        catch(DataAccessException e){
            logger.error(e.getMessage());
            throw new BusinessException(ReturnNo.INTERNAL_SERVER_ERR, "数据库访问错误");
        }

        return new PageInfo<>(productList);
    }

    /**
     * 用GoodsPo对象找Goods对象
     * @param  productId
     * @return  Goods对象列表，带关联的Product返回
     */
    public Product findProductByID_manual(Long productId) throws BusinessException {
        Product product = null;
        ProductPoExample example = new ProductPoExample();
        ProductPoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(productId);
        try{
            List<ProductAllPo> productPoList = productAllMapper.getProductWithAll(example);

            if (productPoList.size() == 0){
                throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST, "产品id不存在");
            }
            product = cloneObj(productPoList.get(0), Product.class);
            product.addOtherProduct(createListObj(productPoList.get(0).getOtherProduct(), Product.class));
            product.setOnSaleList(createListObj(productPoList.get(0).getOnSaleList(), OnSale.class));
            logger.debug("findProductByID_manual: product = {}", product);
        }
        catch(DataAccessException e){
            logger.error(e.getMessage());
            throw new BusinessException(ReturnNo.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
        return product;
    }}
