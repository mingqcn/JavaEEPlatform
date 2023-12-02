package cn.edu.xmu.restfuldemo;


import cn.edu.xmu.restfuldemo.model.GoodsVo;
import cn.edu.xmu.restfuldemo.util.GoodsFactory;
import cn.edu.xmu.restfuldemo.util.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DemoApplication.class)   //标识本类是一个SpringBootTest
@AutoConfigureMockMvc    //配置模拟的MVC，这样可以不启动服务器测试
@Transactional
public class GoodsControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test   //标识此方法为测试方法
    public void getGoodByIdTest() throws Exception {
        String responseString = this.mvc.perform(get("/goods/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"data\":{\"id\":1,\"goodsSn\":\"2221\",\"name\":\"商品1\",\"brief\":\"商品1描述\",\"picUrl\":null,\"state\":\"发布\",\"unit\":\"台\",\"categoryId\":2,\"brandId\":2,\"specList\":null,\"productList\":[{\"id\":1,\"productSn\":\"111111\",\"name\":\"商品1规格1\",\"originalPrice\":100,\"counterPrice\":90,\"weight\":10,\"stock\":100,\"state\":0},{\"id\":2,\"productSn\":\"111112\",\"name\":\"商品1规格2\",\"originalPrice\":200,\"counterPrice\":200,\"weight\":20,\"stock\":2000,\"state\":0}]},\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

    }

    @Test
    public void searchGoodsByNameTest1() throws Exception {
        String responseString = this.mvc.perform(get("/goods/search?name=墨迹"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse =  "{\"errno\":0,\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void searchGoodsByNameTest2() throws Exception {
        String responseString = this.mvc.perform(get("/goods/search?name=商品2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse =  "{\"errno\":0,\"data\":{\"id\":2,\"goodsSn\":\"2222\",\"name\":\"商品2\",\"brief\":\"商品2描述\",\"picUrl\":null,\"state\":\"未发布\",\"unit\":\"个\",\"categoryId\":2,\"brandId\":2,\"specList\":null,\"productList\":null},\"errmsg\":\"成功\"}";

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

        String expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, false);

        responseString = this.mvc.perform(get("/goods/search?name=红米4X"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        expectedResponse =  "{\"errno\":0,\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, false);

    }

    @Test
    public void createGoodTest1() throws Exception {
        GoodsVo g = GoodsFactory.getInstance().createNoNameGoodVo();

        String goodJson = JacksonUtil.toJson(g);

        String responseString = this.mvc.perform(post("/goods").contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":503,\"errmsg\":\"商品名称不能为空;\"}";

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

        JSONAssert.assertEquals(expectedResponse, responseString, false);

        responseString = this.mvc.perform(get("/goods/search?name=测试商品"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        expectedResponse =  "{\"errno\":0,\"data\":{\"id\":1,\"goodsSn\":\"2221\",\"name\":\"测试商品\",\"brief\":\"商品1描述\",\"picUrl\":null,\"state\":\"未发布\",\"unit\":\"台\",\"categoryId\":2,\"brandId\":2,\"specList\":null,\"productList\":null},\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);


    }

    @Test   //标识此方法为测试方法
    public void delGoodsTest() throws Exception {
        String responseString = this.mvc.perform(delete("/goods/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, false);

        responseString = this.mvc.perform(get("/goods/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        expectedResponse = "{\"errno\":504}";

        JSONAssert.assertEquals(expectedResponse, responseString, false);
    }
}
