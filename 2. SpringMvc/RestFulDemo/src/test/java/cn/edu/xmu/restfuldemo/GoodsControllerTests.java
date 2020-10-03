package cn.edu.xmu.restfuldemo;


import cn.edu.xmu.restfuldemo.controller.vo.GoodsVo;
import cn.edu.xmu.restfuldemo.controller.vo.ProductVo;
import cn.edu.xmu.restfuldemo.model.Goods;
import cn.edu.xmu.restfuldemo.model.SpecItem;
import cn.edu.xmu.restfuldemo.model.Specification;
import cn.edu.xmu.restfuldemo.util.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DemoApplication.class)   //标识本类是一个SpringBootTest
@AutoConfigureMockMvc    //配置模拟的MVC，这样可以不启动服务器测试
public class GoodsControllerTests {

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
        GoodsVo g = this.createGoods();

        String goodJson = JacksonUtil.toJson(g);

        String responseString = this.mvc.perform(post("/goods").contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"data\":{\"id\":1,\"goodsSn\":\"11111\",\"name\":\"红米4X\",\"categoryId\":11,\"brandId\":12,\"brief\":\"红米4X是个好用便宜的手机\",\"specList\":[{\"specName\":\"颜色\",\"specItemList\":[{\"id\":\"1\",\"content\":\"香槟金\"},{\"id\":\"2\",\"content\":\"樱花粉\"},{\"id\":\"3\",\"content\":\"磨砂黑\"}]},{\"specName\":\"内存\",\"specItemList\":[{\"id\":\"1\",\"content\":\"2G\"},{\"id\":\"2\",\"content\":\"3G\"}]},{\"specName\":\"机身存储\",\"specItemList\":[{\"id\":\"1\",\"content\":\"16G\"},{\"id\":\"2\",\"content\":\"32G\"}]},{\"specName\":\"机身存储\",\"specItemList\":[{\"id\":\"1\",\"content\":\"16G\"},{\"id\":\"2\",\"content\":\"32G\"}]}],\"productList\":[{\"id\":null,\"productSn\":\"1-1-1\",\"desc\":null,\"goodsId\":1,\"counterPrice\":110,\"retailPrice\":115,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"1-1-2\",\"desc\":null,\"goodsId\":1,\"counterPrice\":120,\"retailPrice\":125,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"1-2-1\",\"desc\":null,\"goodsId\":1,\"counterPrice\":130,\"retailPrice\":135,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"1-2-2\",\"desc\":null,\"goodsId\":1,\"counterPrice\":140,\"retailPrice\":145,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"2-1-1\",\"desc\":null,\"goodsId\":1,\"counterPrice\":110,\"retailPrice\":115,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"2-1-2\",\"desc\":null,\"goodsId\":1,\"counterPrice\":120,\"retailPrice\":125,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"2-2-1\",\"desc\":null,\"goodsId\":1,\"counterPrice\":130,\"retailPrice\":135,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"2-2-2\",\"desc\":null,\"goodsId\":1,\"counterPrice\":140,\"retailPrice\":145,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"3-1-1\",\"desc\":null,\"goodsId\":1,\"counterPrice\":110,\"retailPrice\":115,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"3-1-2\",\"desc\":null,\"goodsId\":1,\"counterPrice\":120,\"retailPrice\":125,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"3-2-1\",\"desc\":null,\"goodsId\":1,\"counterPrice\":130,\"retailPrice\":135,\"stock\":null,\"weight\":10,\"beOnSale\":null},{\"id\":null,\"productSn\":\"3-2-2\",\"desc\":null,\"goodsId\":1,\"counterPrice\":140,\"retailPrice\":145,\"stock\":null,\"weight\":10,\"beOnSale\":null}],\"picUrl\":null,\"unit\":\"台\",\"beOnSale\":null,\"addTime\":null,\"updateTime\":null,\"modiUser\":null},\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void createGoodTest1() throws Exception {
        GoodsVo g = this.createNoNameGood();

        String goodJson = JacksonUtil.toJson(g);

        String responseString = this.mvc.perform(post("/goods").contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errNo\":503,\"errMsg\":\"商品名称不能为空;\",\"data\":null}";

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

    private GoodsVo createGoods() {
        GoodsVo g = new GoodsVo();
        g.setName("红米4X");
        g.setBrief("红米4X是个好用便宜的手机");
        g.setUnit("台");
        g.setCategoryId(11);
        g.setBrandId(12);

        List<Specification> specificationList = new ArrayList<>(4);

        Specification spec = new Specification();
        spec.setSpecName("颜色");
        List<SpecItem> specItemList = new ArrayList<>(3);
        SpecItem item = new SpecItem();
        item.setId("1");
        item.setContent("香槟金");
        specItemList.add(item);

        item = new SpecItem();
        item.setId("2");
        item.setContent("樱花粉");
        specItemList.add(item);

        item = new SpecItem();
        item.setId("3");
        item.setContent("磨砂黑");
        specItemList.add(item);

        spec.setSpecItemList(specItemList);
        specificationList.add(spec);

        spec = new Specification();
        spec.setSpecName("内存");
        specItemList = new ArrayList<>(2);
        item = new SpecItem();
        item.setId("1");
        item.setContent("2G");
        specItemList.add(item);

        item = new SpecItem();
        item.setId("2");
        item.setContent("3G");
        specItemList.add(item);

        spec.setSpecItemList(specItemList);
        specificationList.add(spec);

        spec = new Specification();
        spec.setSpecName("机身存储");
        specItemList = new ArrayList<>(2);
        item = new SpecItem();
        item.setId("1");
        item.setContent("16G");
        specItemList.add(item);

        item = new SpecItem();
        item.setId("2");
        item.setContent("32G");
        specItemList.add(item);

        spec.setSpecItemList(specItemList);
        specificationList.add(spec);

        spec.setSpecItemList(specItemList);
        specificationList.add(spec);

        g.setSpecList(specificationList);

        List<ProductVo> productVoList = new ArrayList<>(12);
        ProductVo productVo = new ProductVo();
        productVo.setProductSn("1-1-1");
        productVo.setDesc("香槟金2G内存16G存储");
        productVo.setCounterPrice(110);
        productVo.setRetailPrice(115);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("1-1-2");
        productVo.setDesc("香槟金2G内存32G存储");
        productVo.setCounterPrice(120);
        productVo.setRetailPrice(125);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("1-2-1");
        productVo.setDesc("香槟金3G内存16G存储");
        productVo.setCounterPrice(130);
        productVo.setRetailPrice(135);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("1-2-2");
        productVo.setDesc("香槟金3G内存32G存储");
        productVo.setCounterPrice(140);
        productVo.setRetailPrice(145);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-1-1");
        productVo.setDesc("樱花粉2G内存16G存储");
        productVo.setCounterPrice(110);
        productVo.setRetailPrice(115);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-1-2");
        productVo.setDesc("樱花粉2G内存32G存储");
        productVo.setCounterPrice(120);
        productVo.setRetailPrice(125);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-2-1");
        productVo.setDesc("樱花粉3G内存16G存储");
        productVo.setCounterPrice(130);
        productVo.setRetailPrice(135);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-2-2");
        productVo.setDesc("樱花粉3G内存32G存储");
        productVo.setCounterPrice(140);
        productVo.setRetailPrice(145);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-1-1");
        productVo.setDesc("磨砂黑2G内存16G存储");
        productVo.setCounterPrice(110);
        productVo.setRetailPrice(115);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-1-2");
        productVo.setDesc("磨砂黑2G内存32G存储");
        productVo.setCounterPrice(120);
        productVo.setRetailPrice(125);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-2-1");
        productVo.setDesc("磨砂黑3G内存16G存储");
        productVo.setCounterPrice(130);
        productVo.setRetailPrice(135);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-2-2");
        productVo.setDesc("磨砂黑3G内存32G存储");
        productVo.setCounterPrice(140);
        productVo.setRetailPrice(145);
        productVo.setWeight(10);
        productVoList.add(productVo);

        g.setProductList(productVoList);
        return g;

    }

    private GoodsVo createNoNameGood() {
        GoodsVo g = new GoodsVo();
        g.setBrief("红米4X是个好用便宜的手机");
        g.setUnit("台");
        g.setCategoryId(11);
        g.setBrandId(12);

        List<Specification> specificationList = new ArrayList<>(4);

        Specification spec = new Specification();
        spec.setSpecName("颜色");
        List<SpecItem> specItemList = new ArrayList<>(3);
        SpecItem item = new SpecItem();
        item.setId("1");
        item.setContent("香槟金");
        specItemList.add(item);

        item = new SpecItem();
        item.setId("2");
        item.setContent("樱花粉");
        specItemList.add(item);

        item = new SpecItem();
        item.setId("3");
        item.setContent("磨砂黑");
        specItemList.add(item);

        spec.setSpecItemList(specItemList);
        specificationList.add(spec);

        spec = new Specification();
        spec.setSpecName("内存");
        specItemList = new ArrayList<>(2);
        item = new SpecItem();
        item.setId("1");
        item.setContent("2G");
        specItemList.add(item);

        item = new SpecItem();
        item.setId("2");
        item.setContent("3G");
        specItemList.add(item);

        spec.setSpecItemList(specItemList);
        specificationList.add(spec);

        spec = new Specification();
        spec.setSpecName("机身存储");
        specItemList = new ArrayList<>(2);
        item = new SpecItem();
        item.setId("1");
        item.setContent("16G");
        specItemList.add(item);

        item = new SpecItem();
        item.setId("2");
        item.setContent("32G");
        specItemList.add(item);

        spec.setSpecItemList(specItemList);
        specificationList.add(spec);

        spec.setSpecItemList(specItemList);
        specificationList.add(spec);

        g.setSpecList(specificationList);

        List<ProductVo> productVoList = new ArrayList<>(12);
        ProductVo productVo = new ProductVo();
        productVo.setProductSn("1-1-1");
        productVo.setDesc("香槟金2G内存16G存储");
        productVo.setCounterPrice(110);
        productVo.setRetailPrice(115);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("1-1-2");
        productVo.setDesc("香槟金2G内存32G存储");
        productVo.setCounterPrice(120);
        productVo.setRetailPrice(125);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("1-2-1");
        productVo.setDesc("香槟金3G内存16G存储");
        productVo.setCounterPrice(130);
        productVo.setRetailPrice(135);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("1-2-2");
        productVo.setDesc("香槟金3G内存32G存储");
        productVo.setCounterPrice(140);
        productVo.setRetailPrice(145);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-1-1");
        productVo.setDesc("樱花粉2G内存16G存储");
        productVo.setCounterPrice(110);
        productVo.setRetailPrice(115);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-1-2");
        productVo.setDesc("樱花粉2G内存32G存储");
        productVo.setCounterPrice(120);
        productVo.setRetailPrice(125);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-2-1");
        productVo.setDesc("樱花粉3G内存16G存储");
        productVo.setCounterPrice(130);
        productVo.setRetailPrice(135);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("2-2-2");
        productVo.setDesc("樱花粉3G内存32G存储");
        productVo.setCounterPrice(140);
        productVo.setRetailPrice(145);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-1-1");
        productVo.setDesc("磨砂黑2G内存16G存储");
        productVo.setCounterPrice(110);
        productVo.setRetailPrice(115);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-1-2");
        productVo.setDesc("磨砂黑2G内存32G存储");
        productVo.setCounterPrice(120);
        productVo.setRetailPrice(125);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-2-1");
        productVo.setDesc("磨砂黑3G内存16G存储");
        productVo.setCounterPrice(130);
        productVo.setRetailPrice(135);
        productVo.setWeight(10);
        productVoList.add(productVo);

        productVo = new ProductVo();
        productVo.setProductSn("3-2-2");
        productVo.setDesc("磨砂黑3G内存32G存储");
        productVo.setCounterPrice(140);
        productVo.setRetailPrice(145);
        productVo.setWeight(10);
        productVoList.add(productVo);

        g.setProductList(productVoList);
        return g;
    }
}
