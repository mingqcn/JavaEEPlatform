package cn.edu.xmu.restfuldemo.dao;

import cn.edu.xmu.restfuldemo.mapper.GoodsMapper;
import cn.edu.xmu.restfuldemo.model.*;
import cn.edu.xmu.restfuldemo.util.Common;
import cn.edu.xmu.restfuldemo.util.ResponseCode;
import cn.edu.xmu.restfuldemo.util.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static cn.edu.xmu.restfuldemo.util.Common.*;
/**
 * @author Ming Qiu
 **/
@Repository
public class GoodsDao {

    private Logger logger = LoggerFactory.getLogger(GoodsDao.class);

    @Autowired
    private GoodsMapper goodsMapper;
    /**
     * 用GoodsPo对象找Goods对象
     * @param goodsPo 条件对象，所有条件为AND，仅有索引的值可以作为条件
     * @param withProduct 是否带关联的Product（有性能问题，不要一次返回太多Goods对象）
     * @return  Goods对象列表，带关联的Product返回
     */
    public ReturnObject<List<Goods>> findGoods(GoodsPo goodsPo, Boolean withProduct){
        logger.info("findGoods: goodsPo =" + goodsPo+" withProduct = "+withProduct);
        List<GoodsPo> goodsPos = goodsMapper.findGoods(goodsPo);
        logger.info("findGoods: goodsPos =" + goodsPos);
        List<Goods> retGoods = new ArrayList<>(goodsPos.size());
        ProductPo productPo =  null;

        if (withProduct) {
            productPo = new ProductPo();
        }

        for (GoodsPo goodsItem : goodsPos){
            Goods item = new Goods(goodsItem);
            if (withProduct) {
                productPo.setGoodsId(goodsItem.getId());
                List<ProductPo> productPos = goodsMapper.findProduct(productPo);
                List<Product> productList = new ArrayList<>(productPos.size());
                for (ProductPo productItem : productPos) {
                    Product product = new Product(productItem);
                    product = getEffectivePrice(product);
                    productList.add(product);
                }
                item.setProductList(productList);
            }
            retGoods.add(item);
        }
        logger.info("findGoods: retGoods = "+retGoods +", withProduct ="+withProduct);
        return new ReturnObject<>(retGoods);
    }

    /**
     * 用GoodsPo对象找Goods对象
     * @param goodsPo 条件对象，所有条件为AND，仅有索引的值可以作为条件
     * @return  Goods对象列表，带关联的Product返回
     */
    public ReturnObject<List<Goods>> findGoodsWithProduct(GoodsPo goodsPo){
        List<GoodsPo> goodsPos = goodsMapper.findGoodsWithProduct(goodsPo);
        List<Goods> retGoods = new ArrayList<>(goodsPos.size());
        for (GoodsPo goodsItem : goodsPos){
            List<ProductPo> productPos = goodsItem.getProductList();
            Goods item = new Goods(goodsItem);
            List<Product> productList = new ArrayList<>(productPos.size());
            for (ProductPo productItem : productPos) {
                Product product = new Product(productItem);
                product = getEffectivePrice(product);
                productList.add(product);
            }
            item.setProductList(productList);
            retGoods.add(item);
        }
        return new ReturnObject<>(retGoods);
    }

    /**
     * 获得规格的当前有效价格和库存
     * @param product 规格对象
     * @return 规格对象
     */
    private Product getEffectivePrice(Product product){
        List<PriceStockPo> priceList = goodsMapper.findEffectPrice(product.getId());
        if (priceList.size()!=0){
            PriceStockPo priceStockPo = priceList.get(0);
            product.setPriceStockPo(priceStockPo);
        }
        return product;
    }
    /**
     * 创建Goods对象
     * @param goods 传入的Goods对象
     * @return 返回对象ReturnObj
     */
    public ReturnObject<Goods> createGoods(Goods goods){
        GoodsPo goodsPo = goods.gotGoodsPo();
        String seqNum = genSeqNum();
        goodsPo.setGoodsSn("G"+seqNum);
        int ret = goodsMapper.createGoods(goodsPo);
        if (goods.getProductList() != null) {
            for (Product product : goods.getProductList()) {
                ProductPo productPo = product.getProductPo();
                productPo.setProductSn("P"+seqNum+"-"+productPo.getProductSn());
                productPo.setGoodsId(goodsPo.getId());
                ret = goodsMapper.createProduct(productPo);
            }
        }
        ReturnObject<Goods> returnObject = new ReturnObject<>(goods);
        return returnObject;
    }

    /**
     * 修改商品信息
     * @param goods 传入的Goods对象
     * @return 返回对象ReturnObj
     */
    public ReturnObject<Object> modiGoods(Goods goods){
        GoodsPo goodsPo = goods.gotGoodsPo();
        ReturnObject<Object> retObj = null;
        int ret = goodsMapper.updateGoods(goodsPo);
        if (ret == 0 ){
            retObj = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
        } else {
            retObj = new ReturnObject<>();
        }
        return retObj;
    }

    /**
     * 删除商品，连带规格
     * @param id 商品id
     * @return
     */
    public ReturnObject<Object> deleteGoods(Integer id) {
        ReturnObject<Object> retObj = null;
        int ret = goodsMapper.deleteGoods(id);
        goodsMapper.deleteProductByGoodsId(id);
        if (ret == 0) {
            retObj = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
        } else {
            retObj = new ReturnObject<>();
        }
        return retObj;
    }

}
