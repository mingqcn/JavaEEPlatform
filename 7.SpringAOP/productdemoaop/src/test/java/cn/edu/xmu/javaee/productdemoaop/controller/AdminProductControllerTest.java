//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.productdemoaop.controller;

import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.util.JacksonUtil;
import cn.edu.xmu.javaee.productdemoaop.ProductDemoAOPApplication;
import cn.edu.xmu.javaee.productdemoaop.controller.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(classes = ProductDemoAOPApplication.class)
@AutoConfigureMockMvc
@Transactional
public class AdminProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String PRODUCTID = "/admin/products/{id}";
    private static final String PRODUCT = "/admin/products";


    @Test
    public void getProduct1() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, 1550))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", is("欢乐家久宝桃罐头")));
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[?(@.id== '%d' )].name", 1559).value("奥利奥缤纷双果味"));
        //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void searchProductByName2() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCT).contentType("application/json;charset=UTF-8")
                        .param("name", "奥"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.length()").value(0));
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.CREATED.getErrNo())))
                //.andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        ProductDto retObj = JacksonUtil.parseObject(ret, "data", ProductDto.class);

        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, retObj.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())));
                //.andDo(MockMvcResultHandlers.print());

        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, 1580))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())));
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