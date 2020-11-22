package cn.edu.xmu.webfluxdemo.controller;


import cn.edu.xmu.webfluxdemo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author Ming Qiu
 * @date Created in 2020/11/22 17:13
 **/
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureWebTestClient
public class FlashSaleControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getFlashSaleTest(){

        webTestClient.get().uri("/flashsale/timesegments/1/flashsales").exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[?(@.id == 1)].goodsSku.name").isEqualTo("铭瑄GeForce GT 1030显卡")
                .jsonPath("$[?(@.id == 2)].goodsSku.name").isEqualTo("美商海盗船CX650电源")
                .jsonPath("$[?(@.id == 3)].goodsSku.name").isEqualTo("戴尔23.8英寸显示器")
                .jsonPath("$[?(@.id == 4)].goodsSku.name").isEqualTo("一晨卫生纸");
    }

}
