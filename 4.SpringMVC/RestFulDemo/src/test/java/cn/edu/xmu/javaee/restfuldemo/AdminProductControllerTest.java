package cn.edu.xmu.javaee.restfuldemo;

import cn.edu.xmu.javaee.core.model.ReturnNo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class AdminProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final static String DRAFT = "/shops/{shopId}/draftproducts";
    private final static String DRAFTID = "/shops/{shopId}/draftproducts/{id}";

    @Test
    public void createDraft() throws Exception {
        String body="{\n" +
                "\"name\":\"好好学习\"," +
                "\"originalPrice\":2000," +
                "\"originPlace\":\"厦门\"," +
                "\"unit\":10" +
                "}";

        this.mockMvc.perform(MockMvcRequestBuilders.post(DRAFT,4L)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.CREATED.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", is("好好学习")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.originalPrice", is(2000)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.originPlace", is("厦门")));
        //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void delProducts() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(DRAFTID,2L, 1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())));

    }

    @Test
    public void delProductsGivenNonExistID() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(DRAFTID,2L, 11L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.RESOURCE_ID_NOTEXIST.getErrNo())));

    }

    @Test
    public void delProductsGivenWrongShop() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(DRAFTID,4L, 1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.RESOURCE_ID_OUTSCOPE.getErrNo())));
    }

    @Test
    public void modifyDraft() throws Exception{
        String body="{\n" +
                "\"name\":\"天天向上\"," +
                "\"unit\":10" +
                "}";

        this.mockMvc.perform(MockMvcRequestBuilders.put(DRAFTID,2L, 1L)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())));

    }

    @Test
    public void modifyDraftGivenNonExistID() throws Exception{
        String body="{\n" +
                "\"name\":\"天天向上\"," +
                "\"unit\":10" +
                "}";

        this.mockMvc.perform(MockMvcRequestBuilders.put(DRAFTID,2L, 101L)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.RESOURCE_ID_NOTEXIST.getErrNo())));

    }

    @Test
    public void modifyDraftGivenWrongShop() throws Exception{
        String body="{\n" +
                "\"name\":\"天天向上\"," +
                "\"unit\":10" +
                "}";

        this.mockMvc.perform(MockMvcRequestBuilders.put(DRAFTID,4L, 1L)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.RESOURCE_ID_OUTSCOPE.getErrNo())));

    }

    @Test
    public void getProducts() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(DRAFTID,2L, 1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.OK.getErrNo())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", is("测试1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.originalPrice", is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.originPlace", is("厦门")));
    }

    @Test
    public void getProductsGivenNonExistID() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(DRAFTID,2L, 11L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.RESOURCE_ID_NOTEXIST.getErrNo())));
    }

    @Test
    public void getProductsGivenWrongShop() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(DRAFTID,4L, 1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno", is(ReturnNo.RESOURCE_ID_OUTSCOPE.getErrNo())));
    }

}
