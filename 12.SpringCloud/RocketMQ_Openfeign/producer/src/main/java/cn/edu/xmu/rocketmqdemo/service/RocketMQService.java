package cn.edu.xmu.rocketmqdemo.service;


import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Ming Qiu
 * @date Created in 2020/11/7 22:53
 **/
@Service
public class RocketMQService {

    private static final Logger logger = LoggerFactory.getLogger(RocketMQService.class);

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendOrderPayMessage(Long orderId){
        logger.info("sendOrderPayMessage: send message orderId = "+orderId+" time =" +LocalDateTime.now());
        rocketMQTemplate.asyncSend("order-pay-topic", MessageBuilder.withPayload(orderId.toString()).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                logger.info("sendOrderPayMessage: onSuccess result = "+ sendResult+" time ="+LocalDateTime.now());
            }

            @Override
            public void onException(Throwable throwable) {
                logger.info("sendOrderPayMessage: onException e = "+ throwable.getMessage()+" time ="+LocalDateTime.now());
            }
        });
    }
}
