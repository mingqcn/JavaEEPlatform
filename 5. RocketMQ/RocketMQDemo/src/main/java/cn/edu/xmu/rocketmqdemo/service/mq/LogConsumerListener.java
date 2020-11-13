package cn.edu.xmu.rocketmqdemo.service.mq;

import cn.edu.xmu.rocketmqdemo.model.Log;
import cn.edu.xmu.rocketmqdemo.util.JacksonUtil;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 消息消费者
 * @author Ming Qiu
 * @date Created in 2020/11/7 22:47
 **/
@Service
@RocketMQMessageListener(topic = "log-topic", selectorExpression = "1", consumeMode = ConsumeMode.CONCURRENTLY, consumeThreadMax = 10, consumerGroup = "log-group")
public class LogConsumerListener implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
    private static final Logger logger = LoggerFactory.getLogger(LogConsumerListener.class);
    @Override
    public void onMessage(String message) {
        Log log = JacksonUtil.toObj(message, Log.class);
        logger.info("onMessage: got message log =" + log);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
//        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
//        defaultMQPushConsumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()));
        logger.info("prepareStart: consumergroup =" + defaultMQPushConsumer.getConsumerGroup());
    }
}
