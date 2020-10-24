package cn.edu.xmu.restfuldemo.mapper;

import cn.edu.xmu.restfuldemo.model.GoodsPo;
import cn.edu.xmu.restfuldemo.model.PriceStockPo;
import cn.edu.xmu.restfuldemo.model.ProductPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Ming Qiu
 **/
@Mapper
public interface GoodsMapper {
     /**
      * 用GoodsPo对象找，
      * @param goodsPo 条件对象，所有条件为AND，仅有索引的值可以作为条件
      * @return  GoodsPo对象列表
      */
     List<GoodsPo> findGoods(GoodsPo goodsPo);

     /**
      * 用GoodsPo对象找，带Product对象返回
      * @param goodsPo 条件对象，所有条件为AND，仅有索引的值可以作为条件
      * @return  GoodsPo对象列表
      */
     List<GoodsPo> findGoodsWithProduct(GoodsPo goodsPo);

     /**
      * 用ProductPo对象找，
      * @param productPo 条件对象，所有条件为AND，仅有索引的值可以作为条件
      * @return  ProductPo对象列表
      */
     List<ProductPo> findProduct(ProductPo productPo);

     /**
      * 创建GoodsPo对象
      * @param goodsPo goodsPo对象
      * @return 1 成功
      */
     int createGoods(GoodsPo goodsPo);

     /**
      * 创建ProductPo对象
      * @param productPo productPo对象
      * @return 1 成功
      */
     int createProduct(ProductPo productPo);

     /**
      * 用GoodsPo对象修改Goods对象
      * @param goodsPo 修改Goods对象
      * @return
      */
     int updateGoods(GoodsPo goodsPo);

     /**
      * 删除Goods对象，逻辑删除，
      * @param id 删除对象的id
      * @return
      */
     int deleteGoods(Integer id);

     /**
      * 删除goods对象的所有Product对象，逻辑删除，
      * @param id 删除的goods对象id
      * @return
      */
     int deleteProductByGoodsId(Integer id);

     /**
      * 删除Product对象，逻辑删除，
      * @param id 删除对象的id
      * @return
      */
     int deleteProduct(Integer id);

     /**
      * 获得Product的当前浮动价格和库存
      * @param id product id
      * @return 当前浮动价格和库存
      */
     List<PriceStockPo> findEffectPrice(Integer id);
}
