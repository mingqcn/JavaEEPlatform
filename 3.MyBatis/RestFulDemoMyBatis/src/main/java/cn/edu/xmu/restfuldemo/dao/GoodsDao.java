package cn.edu.xmu.restfuldemo.dao;

import cn.edu.xmu.restfuldemo.mapper.GoodsMapper;
import cn.edu.xmu.restfuldemo.model.Goods;
import cn.edu.xmu.restfuldemo.model.GoodsPo;
import cn.edu.xmu.restfuldemo.model.Product;
import cn.edu.xmu.restfuldemo.model.ProductPo;
import cn.edu.xmu.restfuldemo.util.ResponseCode;
import cn.edu.xmu.restfuldemo.util.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming Qiu
 **/
@Repository
public class GoodsDao {

    @Autowired
    private GoodsMapper goodsMapper;
    /**
     * 用GoodsPo对象找Goods对象
     * @param goodsPo 条件对象，所有条件为AND，仅有索引的值可以作为条件
     * @param withProduct 是否带关联的Product（有性能问题，不要一次返回太多Goods对象）
     * @return  Goods对象列表，带关联的Product返回
     */
    public ReturnObject<List<Goods>> findGoods(GoodsPo goodsPo, Boolean withProduct){
        goodsPo.setBeDeleted(false);
        List<GoodsPo> goodsPos = goodsMapper.findGoods(goodsPo);
        List<Goods> retGoods = new ArrayList<>(goodsPos.size());
        ProductPo productPo =  null;

        if (withProduct) {
            productPo = new ProductPo();
        }

        for (GoodsPo goodsItem : goodsPos){
            List<Product> productList = null;

            if (withProduct) {
                productPo.setGoodsId(goodsItem.getId());
                List<ProductPo> productPos = goodsMapper.findProduct(productPo);
                productList = new ArrayList<>(productPos.size());
                for (ProductPo productItem : productPos) {
                    Product product = new Product(productItem);
                    productList.add(product);
                }
            }

            Goods item = new Goods(goodsItem);

            if (withProduct) {
                item.setProductList(productList);
            }
            retGoods.add(item);
        }
        return new ReturnObject<>(retGoods);
    }

    /**
     * 创建Goods对象
     * @param goods 传入的Goods对象
     * @return 返回对象ReturnObj
     */
    public ReturnObject<Goods> createGoods(Goods goods){
        GoodsPo goodsPo = goods.gotGoodsPo();
        int ret = goodsMapper.createGoods(goodsPo);
        if (goods.getProductList() != null) {
            for (Product product : goods.getProductList()) {
                ProductPo productPo = product.getProductPo();
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
            retObj = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST, ResponseCode.RESOURCE_ID_NOTEXIST_MSG);
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
            retObj = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST, ResponseCode.RESOURCE_ID_NOTEXIST_MSG);
        } else {
            retObj = new ReturnObject<>();
        }
        return retObj;
    }
}
