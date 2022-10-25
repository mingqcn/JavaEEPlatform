package cn.edu.xmu.javaee.goodsdemo.service;

import cn.edu.xmu.javaee.core.model.SimpleUser;
import cn.edu.xmu.javaee.goodsdemo.dao.ProductDao;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.Product;
import cn.edu.xmu.javaee.core.util.BusinessException;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductService.class);


    private ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * 获取某个商品信息，仅展示相关内容
     *
     * @param id 商品id
     * @return 商品对象
     */
    @Transactional(rollbackFor = {BusinessException.class})
    public Product retrieveProductByID(Long id, boolean all) throws BusinessException {
        logger.debug("findProductById: id = {}, all = {}", id, all);
        return productDao.retrieveProductByID(id, all);
    }

    /**
     * 用商品名称搜索商品
     *
     * @param name 商品名称
     * @return 商品对象
     */
    @Transactional(rollbackFor = {BusinessException.class})
    public PageInfo<Product> retrieveProductByName(String name, boolean all, int page, int pageSize) throws BusinessException{
        return productDao.retrieveProductByName(name, all, page, pageSize);
    }

    /**
     * 新增商品
     * @param product 新商品信息
     * @return 新商品
     */
    @Transactional(rollbackFor = {BusinessException.class})
    public Product createProduct(Product product, SimpleUser userVo) throws BusinessException{
        return productDao.createProduct(product, userVo);
    }


    /**
     * 修改商品
     * @param product 修改商品信息
     */
    @Transactional(rollbackFor = {BusinessException.class})
    public void modifyProduct(Product product, SimpleUser userVo) throws BusinessException{
        productDao.modiProduct(product, userVo);
    }

    /** 删除商品
     * @param id 商品id
     * @return 删除是否成功
     */
    @Transactional(rollbackFor = {BusinessException.class})
    public void deleteProduct(Long id, SimpleUser user) throws BusinessException {
        productDao.deleteProduct(id, user);
    }

    @Transactional(rollbackFor = {BusinessException.class})
    public Product findProductById_manual(Long id) throws BusinessException {
        logger.debug("findProductById_manual: id = {}", id);
        return productDao.findProductByID_manual(id);
    }

    /**
     * 用商品名称搜索商品
     *
     * @param name 商品名称
     * @return 商品对象
     */
    @Transactional(rollbackFor = {BusinessException.class})
    public PageInfo<Product> findProductByName_manual(String name, int page, int pageSize) throws BusinessException{
        return productDao.findProductByName_manual(name, page, pageSize);
    }

}
