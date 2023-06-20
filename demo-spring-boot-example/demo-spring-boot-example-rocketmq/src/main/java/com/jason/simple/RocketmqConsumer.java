package com.jason.simple;

import com.jason.message.MessageObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author jason
 * @date 2023/6/6
 */
@Slf4j
@Component
public class RocketmqConsumer {

    /***
     * CLUSTERING 默认是集群消费模式
     */
    @Component
    @RocketMQMessageListener(topic = "topic-queue-one", consumerGroup = "consumer_topic-queue-one",messageModel = MessageModel.CLUSTERING)
    public class ConsumerOne implements RocketMQListener<MessageObject> {
        @Override
        public void onMessage(MessageObject messageObject) {
            log.info("consumer-one received message: {}", messageObject);
        }
    }

    @Component
    @RocketMQMessageListener(topic = "topic-queue-two", consumerGroup = "consumer_topic-queue-two")
    public class ConsumerTwo implements RocketMQListener<String> {
        @Override
        public void onMessage(String message) {
            System.out.println("哈哈哈哈我进来消费  topic-queue-two 消息啦");
            log.info("consumer-two received message: {}", message);
        }
    }

    /***
     * 异步测试消费
     */
    @Component
//    @RocketMQMessageListener(topic = "async-one", consumerGroup = "consumer_topic-queue-three")
    public class ConsumerThree implements RocketMQListener<MessageObject> {
        @Override
        public void onMessage(MessageObject messageObject) {
            System.out.println("哈哈哈哈我进来消费  async-one 消息啦");
            log.info("consumer-two received message: {}", messageObject);
        }
    }

    /***
     * 单向发送
     */
    @Component
//    @RocketMQMessageListener(topic = "topic-oneWay", consumerGroup = "consumer_topic-queue-three")
    public class ConsumerOneWay implements RocketMQListener<String> {
        @Override
        public void onMessage(String message) {
            System.out.println("哈哈哈哈我进来消费  topic-oneWay 消息啦");
            log.info("consumer-two received message: {}", message);
        }
    }
}
