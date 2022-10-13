//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.controller;

import cn.edu.xmu.javaee.goodsdemo.GoodsDemoApplication;
import cn.edu.xmu.javaee.goodsdemo.util.ReturnNo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.*;

@SpringBootTest(classes = GoodsDemoApplication.class)
@AutoConfigureMockMvc
@Transactional
public class ProductControllerTest {

    private MockMvc mockMvc;

    private static final String PRODUCTID ="/products/{id}";



    @Autowired
    public ProductControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void getProduct1() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID,1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[?(@.id=='1550')].name", is("欢乐家久宝桃罐头")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[?(@.id=='1550')].price", is("53295")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[?(@.id=='1550')].quantity", is("2000")));

    }
}
