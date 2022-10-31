//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.controller;

import cn.edu.xmu.javaee.core.util.JwtHelper;
import cn.edu.xmu.javaee.goodsdemo.GoodsDemoAopApplication;
import cn.edu.xmu.javaee.goodsdemo.controller.vo.ProductVo;
import cn.edu.xmu.javaee.core.util.JacksonUtil;
import cn.edu.xmu.javaee.core.util.ReturnNo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(classes = GoodsDemoAopApplication.class)
@AutoConfigureMockMvc
@Transactional
public class AdminProductControllerTest {

    private String adminToken;

    private MockMvc mockMvc;

    private static final String PRODUCTID = "/admin/products/{id}";
    private static final String PRODUCT = "/admin/products";

    @Autowired
    public AdminProductControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @BeforeEach
    public void setup(){
        JwtHelper jwtHelper = new JwtHelper();
        this.adminToken = jwtHelper.createToken(1L, "13088admin", 0L, 1, 3600);
    }


    @Test
    public void getProduct1() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, 1550)
                        .header("authorization", adminToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", is("欢乐家久宝桃罐头")));
        //.andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getProduct2() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, 1550122)
                        .header("authorization", adminToken))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        //.andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void searchProductByName1() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCT).contentType("application/json;charset=UTF-8")
                        .header("authorization", adminToken)
                        .param("name", "奥利奥缤纷双果味"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[?(@.id== '%d' )].name", 1559).value("奥利奥缤纷双果味"));
        //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void searchProductByName2() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCT).contentType("application/json;charset=UTF-8")
                        .header("authorization", adminToken)
                        .param("name", "奥"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list.length()").value(0));
        //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createProduct1() throws Exception {

        String body = "{\"name\":\"水果糖\",\"originalPrice\":100,\"weight\":807,\"barcode\":\"1234455\",\"unit\":\"盒\",\"originPlace\":\"长沙\"}";
        String ret = this.mockMvc.perform(MockMvcRequestBuilders.post(PRODUCT)
                        .header("authorization", adminToken)
                        .content(body.getBytes("utf-8"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.CREATED.getErrNo())))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        ProductVo retObj = JacksonUtil.parseObject(ret, "data", ProductVo.class);

        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, retObj.getId())
                        .header("authorization", adminToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", is("水果糖")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.creator.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.creator.name", is("13088admin")));
                //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createProduct2() throws Exception {

        String body = "{\"name\":\"水果糖\",\"originalPrice\":-1,\"weight\":807,\"barcode\":\"1234455\",\"unit\":\"盒\",\"originPlace\":\"长沙\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post(PRODUCT)
                        .content(body.getBytes("utf-8"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("authorization", adminToken))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.FIELD_NOTVALID.getErrNo())));
                //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createProduct3() throws Exception {

        String body = "{\"name\":\"水果糖\",\"originalPrice\":100,\"weight\":807,\"barcode\":\"1234455\",\"unit\":\"盒\",\"originPlace\":\"长沙\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post(PRODUCT)
                        .content(body.getBytes("utf-8"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.AUTH_NEED_LOGIN.getErrNo())));
                //.andDo(MockMvcResultHandlers.print())

    }

    @Test
    public void createProduct4() throws Exception {

        String body = "{\"name\":\"水果糖\",\"originalPrice\":100,\"weight\":807,\"barcode\":\"1234455\",\"unit\":\"盒\",\"originPlace\":\"长沙\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post(PRODUCT)
                        .header("authorization", "123456787")
                        .content(body.getBytes("utf-8"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.AUTH_INVALID_JWT.getErrNo())));
                //.andDo(MockMvcResultHandlers.print())

    }
    @Test
    public void modiProduct1() throws Exception {
        String body = "{\"name\":\"奶糖\",\"originalPrice\":200}";
        this.mockMvc.perform(MockMvcRequestBuilders.put(PRODUCTID, 1580)
                        .content(body.getBytes("utf-8"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("authorization", adminToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())));
                //.andDo(MockMvcResultHandlers.print());

        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, 1580)
                        .header("authorization", adminToken))
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
                        .header("authorization", adminToken)
                        .content(body.getBytes("utf-8"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
                //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void delProduct1() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(PRODUCTID, 1580)
                        .header("authorization", adminToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
                .andDo(MockMvcResultHandlers.print());

        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, 1580)
                        .header("authorization", adminToken))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void delProduct2() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(PRODUCTID, 1580112)
                        .header("authorization", adminToken))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        //.andDo(MockMvcResultHandlers.print());
    }

}