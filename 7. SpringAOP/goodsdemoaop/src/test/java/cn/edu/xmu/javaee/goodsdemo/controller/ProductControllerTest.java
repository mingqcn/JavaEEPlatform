//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.controller;

import cn.edu.xmu.javaee.goodsdemo.GoodsDemoAopApplication;
import cn.edu.xmu.javaee.core.util.ReturnNo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.*;

@SpringBootTest(classes = GoodsDemoAopApplication.class)
@AutoConfigureMockMvc
@Transactional
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String PRODUCTID = "/customer/products/{id}";
    private static final String PRODUCT = "/customer/products";


    @Test
    public void getProduct1() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, 1550))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", is("欢乐家久宝桃罐头")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.price", is(53295)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.quantity", is(2000)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct.length()").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 2079).value("瓜果刨"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 2358).value("梅花味精"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 2439).value("维达果园飘香迷你纸"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 2929).value("干一杯"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 3056).value("娃哈哈原味"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getProduct2() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, 1550122))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        //.andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getProduct3() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, 1574)
                        .param("type","manual"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", is("万丽洁厕精")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.price", is(3678)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.quantity", is(30)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct.length()").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 1792).value("好迪超爽特硬啫哩水"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 1999).value("飘柔洗发露"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 2983).value("乌江菜上皇爽口下饭菜168"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 4551).value("川灵三丝"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.otherProduct[?(@.id== '%d' )].name", 5338).value("神田炖排骨料30"));
        //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getProduct4() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTID, 1550122).param("type","manual"))
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[?(@.id== '%d' )].name", 1559).value("奥利奥缤纷双果味"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void searchProductByName2() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCT).contentType("application/json;charset=UTF-8")
                        .param("name", "奥"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list.length()").value(0));
        //.andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void searchProductByName3() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCT).contentType("application/json;charset=UTF-8")
                        .param("name", "圣峰冰茶牙膏")
                        .param("type","manual"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[?(@.id== '%d' )].name", 1605).value("圣峰冰茶牙膏"));
        //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void searchProductByName4() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PRODUCT).contentType("application/json;charset=UTF-8")
                        .param("name", "奥")
                        .param("type","manual"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list.length()").value(0));
        //.andDo(MockMvcResultHandlers.print());
    }
}