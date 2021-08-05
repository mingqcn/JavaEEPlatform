package cn.edu.xmu.restfuldemo.controller;


import cn.edu.xmu.restfuldemo.DemoApplication;
import cn.edu.xmu.restfuldemo.controller.vo.GoodsVo;
import cn.edu.xmu.restfuldemo.util.GoodsFactory;
import cn.edu.xmu.restfuldemo.util.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DemoApplication.class)   //标识本类是一个SpringBootTest
@AutoConfigureMockMvc    //配置模拟的MVC，这样可以不启动服务器测试
public class GoodsControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test   //标识此方法为测试方法
    public void getGoodByIdTest() throws Exception {
        String responseString = this.mvc.perform(get("/goods/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"data\":{\"id\":1,\"goodsSn\":\"111111\",\"name\":\"红米4X\",\"categoryId\":null,\"brandId\":null,\"brief\":\"红米4X是个好用便宜的手机\",\"specList\":[{\"specName\":\"颜色\",\"specItemList\":[{\"id\":\"1\",\"content\":\"香槟金\"},{\"id\":\"2\",\"content\":\"樱花粉\"},{\"id\":\"3\",\"content\":\"磨砂黑\"}]},{\"specName\":\"内存\",\"specItemList\":[{\"id\":\"1\",\"content\":\"2G\"},{\"id\":\"2\",\"content\":\"3G\"}]},{\"specName\":\"机身存储\",\"specItemList\":[{\"id\":\"1\",\"content\":\"16G\"},{\"id\":\"2\",\"content\":\"32G\"}]},{\"specName\":\"机身存储\",\"specItemList\":[{\"id\":\"1\",\"content\":\"16G\"},{\"id\":\"2\",\"content\":\"32G\"}]}],\"productList\":null,\"picUrl\":null,\"unit\":\"台\",\"beOnSale\":null,\"addTime\":null,\"updateTime\":null,\"modiUser\":null},\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

    }

    @Test
    public void searchGoodsByNameTest() throws Exception {
        String responseString = this.mvc.perform(get("/goods/search?name=墨迹"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse =  "{\"errno\":0,\"data\":{\"id\":1,\"goodsSn\":\"111111\",\"name\":\"墨迹\",\"categoryId\":null,\"brandId\":null,\"brief\":\"红米4X是个好用便宜的手机\",\"specList\":[{\"specName\":\"颜色\",\"specItemList\":[{\"id\":\"1\",\"content\":\"香槟金\"},{\"id\":\"2\",\"content\":\"樱花粉\"},{\"id\":\"3\",\"content\":\"磨砂黑\"}]},{\"specName\":\"内存\",\"specItemList\":[{\"id\":\"1\",\"content\":\"2G\"},{\"id\":\"2\",\"content\":\"3G\"}]}],\"productList\":null,\"picUrl\":null,\"unit\":\"台\",\"beOnSale\":null,\"addTime\":null,\"updateTime\":null,\"modiUser\":null},\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void createGoodTest() throws Exception {
        GoodsVo g =  GoodsFactory.getInstance().createGoodsVo();

        String goodJson = JacksonUtil.toJson(g);

        String responseString = this.mvc.perform(post("/goods").contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"data\":{\"id\":1,\"goodsSn\":\"11111\",\"name\":\"红米4X\",\"categoryId\":11,\"brandId\":12,\"brief\":\"红米4X是个好用便宜的手机\",\"specList\":[{\"specName\":\"颜色\",\"specItemList\":[{\"id\":\"1\",\"content\":\"香槟金\"},{\"id\":\"2\",\"content\":\"樱花粉\"},{\"id\":\"3\",\"content\":\"磨砂黑\"}]},{\"specName\":\"内存\",\"specItemList\":[{\"id\":\"1\",\"content\":\"2G\"},{\"id\":\"2\",\"content\":\"3G\"}]},{\"specName\":\"机身存储\",\"specItemList\":[{\"id\":\"1\",\"content\":\"16G\"},{\"id\":\"2\",\"content\":\"32G\"}]}],\"productList\":[{\"id\":null,\"productSn\":\"1-1-1\",\"desc\":null,\"goodsId\":1,\"counterPrice\":110,\"retailPrice\":115,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"1-1-2\",\"desc\":null,\"goodsId\":1,\"counterPrice\":120,\"retailPrice\":125,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"1-2-1\",\"desc\":null,\"goodsId\":1,\"counterPrice\":130,\"retailPrice\":135,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"1-2-2\",\"desc\":null,\"goodsId\":1,\"counterPrice\":140,\"retailPrice\":145,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"2-1-1\",\"desc\":null,\"goodsId\":1,\"counterPrice\":110,\"retailPrice\":115,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"2-1-2\",\"desc\":null,\"goodsId\":1,\"counterPrice\":120,\"retailPrice\":125,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"2-2-1\",\"desc\":null,\"goodsId\":1,\"counterPrice\":130,\"retailPrice\":135,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"2-2-2\",\"desc\":null,\"goodsId\":1,\"counterPrice\":140,\"retailPrice\":145,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"3-1-1\",\"desc\":null,\"goodsId\":1,\"counterPrice\":110,\"retailPrice\":115,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"3-1-2\",\"desc\":null,\"goodsId\":1,\"counterPrice\":120,\"retailPrice\":125,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"3-2-1\",\"desc\":null,\"goodsId\":1,\"counterPrice\":130,\"retailPrice\":135,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"3-2-2\",\"desc\":null,\"goodsId\":1,\"counterPrice\":140,\"retailPrice\":145,\"stock\":null,\"weight\":10,\"beOnSale\":null}],\"picUrl\":null,\"unit\":\"台\",\"beOnSale\":null,\"addTime\":null,\"updateTime\":null,\"modiUser\":null},\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void createGoodTest1() throws Exception {
        GoodsVo g = GoodsFactory.getInstance().createNoNameGoodVo();

        String goodJson = JacksonUtil.toJson(g);

        String responseString = this.mvc.perform(post("/goods").contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"errmsg\":\"商品名称不能为空\",\"data\":null}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void modiGoodTest() throws Exception {
        GoodsVo g = new GoodsVo();
        g.setName("测试商品");

        String goodJson = JacksonUtil.toJson(g);

        String responseString = this.mvc.perform(put("/goods/1").contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();
        String expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test   //标识此方法为测试方法
    public void delGoodsTest() throws Exception {
        String responseString = this.mvc.perform(delete("/goods/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }
}
