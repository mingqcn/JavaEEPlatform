package cn.edu.xmu.oomall.payment.controller;//School of Informatics Xiamen University, GPL-3.0 license

import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.util.JacksonUtil;
import cn.edu.xmu.javaee.core.util.JwtHelper;
import cn.edu.xmu.javaee.core.util.RedisUtil;
import cn.edu.xmu.oomall.payment.PaymentApplication;
import cn.edu.xmu.oomall.payment.service.openfeign.WePayService;
import cn.edu.xmu.oomall.payment.service.openfeign.param.PostTransRetObj;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(classes = PaymentApplication.class)
@AutoConfigureMockMvc
@Transactional
public class InternalPaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RedisUtil redisUtil;

    @MockBean
    private WePayService wePayService;

    private static String adminToken;

    private static final String ORDERS_PRODUCT = "/internal/payments";

    @BeforeAll
    public static void setup(){
        JwtHelper jwtHelper = new JwtHelper();
        adminToken = jwtHelper.createToken(1L, "13088admin", 0L, 1, 3600);
    }

    @Test
    public void createPayment1() throws Exception {
        PostTransRetObj retObj = new PostTransRetObj();
        retObj.setPrepayId("111111");
        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        Mockito.when(wePayService.postTransaction(Mockito.any())).thenReturn(retObj);

        String body = "{\"spOpenid\":\"10000\",\"amount\":100,\"shopChannelId\":501,\"businessId\":501,\"outNo\":\"xxxx\"}";
        String ret = this.mockMvc.perform(MockMvcRequestBuilders.post(ORDERS_PRODUCT, 100)
                        .header("authorization", adminToken)
                        .content(body.getBytes("utf-8"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.CREATED.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.prepayId", is("111111")))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

         Long payTransId = JacksonUtil.parseObject(ret, "data.id", Long.class);

    }

    @Test
    public void createPayment2() throws Exception {
        String body = "{\"spOpenid\":\"10000\",\"amount\":100,\"shopChannelId\":501,\"businessaa\":501}";
        this.mockMvc.perform(MockMvcRequestBuilders.post(ORDERS_PRODUCT, 100)
                        .header("authorization", adminToken)
                        .content(body.getBytes("utf-8"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.FIELD_NOTVALID.getErrNo())));
    }

}