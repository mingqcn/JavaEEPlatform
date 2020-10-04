package cn.edu.xmu.restfuldemo.service;

import cn.edu.xmu.restfuldemo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import cn.edu.xmu.restfuldemo.model.Goods;
import cn.edu.xmu.restfuldemo.service.GoodsService;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author: Ming Qiu
 * @date: Created in 22:06 2020/8/1
 **/
@SpringBootTest(classes = DemoApplication.class)
public class GoodsServiceTest {

    @Autowired
    private GoodsService  goodsService;

    @Test
    void findById() {
        Goods goods = goodsService.findById(1);
        assertEquals(1, goods.getId());
        assertEquals("111111", goods.getGoodsSn());
    }
}
