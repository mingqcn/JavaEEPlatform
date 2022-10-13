//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.controller;

import cn.edu.xmu.javaee.goodsdemo.GoodsDemoApplication;
import cn.edu.xmu.javaee.goodsdemo.controller.vo.ProductVo;
import cn.edu.xmu.javaee.goodsdemo.util.JacksonUtil;
import cn.edu.xmu.javaee.goodsdemo.util.ReturnNo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.*;

@SpringBootTest(classes = GoodsDemoApplication.class)
@AutoConfigureMockMvc
@Transactional
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String PRODUCTID = "/products/{id}";
    private static final String PRODUCT = "/products";


    @Test
    public void getProduct1() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, 1550))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", is("欢乐家久宝桃罐头")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.price", is(53295)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.quantity", is(2000)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct.length()").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 2079).value("瓜果刨"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 2358).value("梅花味精"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 2439).value("维达果园飘香迷你纸"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 2929).value("干一杯"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 3056).value("娃哈哈原味"));
        //.andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getProduct2() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, 1550122))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        //.andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void searchProductByName1() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCT).contentType("application/json;charset=UTF-8")
                        .param("name", "奥利奥缤纷双果味"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[?(@.id== '%d' )].name", 1559).value("奥利奥缤纷双果味"));
        //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createProduct1() throws Exception {

        String body = "{\"name\":\"水果糖\",\"originalPrice\":100,\"weight\":807,\"barcode\":\"1234455\",\"unit\":\"盒\",\"originPlace\":\"长沙\"}";
        String ret = this.mockMvc.perform(MockMvcRequestBuilders.post(PRODUCT)
                        .content(body.getBytes("utf-8"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getCode())))
                //.andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        ProductVo retObj = JacksonUtil.parseObject(ret, "data", ProductVo.class);
        System.out.print(retObj);

        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, retObj.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", is("水果糖")));
        //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void modiProduct1() throws Exception {
        String body = "{\"name\":\"奶糖\",\"originalPrice\":200}";
        this.mockMvc.perform(MockMvcRequestBuilders.put(PRODUCTID, 1580)
                        .content(body.getBytes("utf-8"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getCode())));
                //.andDo(MockMvcResultHandlers.print());

        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, 1580))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", is("奶糖")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.originalPrice", is(200)));
                //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void modiProduct2() throws Exception {
        String body = "{\"name\":\"奶糖\",\"originalPrice\":200}";
        this.mockMvc.perform(MockMvcRequestBuilders.put(PRODUCTID, 158011)
                        .content(body.getBytes("utf-8"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
                //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void delProduct1() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(PRODUCTID, 1580))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getCode())));
        //.andDo(MockMvcResultHandlers.print());

        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, 1580))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void delProduct2() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(PRODUCTID, 1580112))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        //.andDo(MockMvcResultHandlers.print());
    }

}