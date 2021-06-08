package cn.edu.xmu.restfuldemo.controller.vo;

import cn.edu.xmu.restfuldemo.model.Goods;
import cn.edu.xmu.restfuldemo.util.GoodsFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ming Qiu
 **/
public class GoodsVoTest {

    @Test
    public void createGoodsTest(){
        GoodsVo goodsVo= GoodsFactory.getInstance().createGoodsVo();
        Goods goods = goodsVo.createGoods();
        assertEquals("红米4X", goods.getName() );
        assertEquals("红米4X是个好用便宜的手机", goods.getBrief() );
        assertEquals("台", goods.getUnit());
        assertEquals(11, goods.getCategoryId());
        assertEquals(12, goods.getBrandId());
        assertEquals(3, goods.getSpecList().size());
    }
}
