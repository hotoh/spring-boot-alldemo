package com.jason.simple;

import com.jason.message.MessageObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @author jason
 * @date 2023/6/6
 */
@Component
@Slf4j
public class RocketmqProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private static MessageChannel output; // 获取name为output的binding

    //同步发送
    public static void syncMessageByOriginApi() throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        MessageObject<String> messageObject = new MessageObject<>();
        messageObject.setId(UUID.randomUUID().toString());
        messageObject.setContent("Hello, springboot-ac-rocketmq !");

        // RocketMQ 原生API调用发送
        DefaultMQProducer producer = new DefaultMQProducer("rocketmq-group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        for (int i = 0; i < 2; i++) {
            Message message = new Message("topic-one", "Tags", "Hello, springboot-ac-rocketmq !".getBytes());
            SendResult sendResult = producer.send(message);
            System.out.println(i + " send result success: " + sendResult);
        }
        producer.shutdown();

    }

    public static void syncMessageByMessageChannel() {
        // Spring Cloud Stream 调用RocketMQ
        Message msg = new Message("test-topic", "tagStr", "message from rocketmq producer sync steam".getBytes());
        Map<String, Object> headers = new HashMap<>();
        headers.put(MessageConst.PROPERTY_TAGS, "tagStr");
        org.springframework.messaging.Message<Message> message = MessageBuilder.createMessage(msg, new MessageHeaders(headers));
        boolean sendResult = output.send(message);
        System.out.println("send result: " + sendResult);
    }

    public void async() {
        MessageObject<String> messageObject = new MessageObject<>();
        messageObject.setId(UUID.randomUUID().toString());
        messageObject.setContent("Hello,I am asyncSend !");
        rocketMQTemplate.asyncSend("async-one", messageObject, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("send successful");
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("send fail; {}", throwable.getMessage());
            }
        });
    }

    public void oneWay() {
        rocketMQTemplate.sendOneWay("topic-oneWay", "send one-way message");
    }

    public static void main(String[] args) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        // 发送同步消息 通过RocketMQ 原生的 API 进行消息发送
        // syncMessageByOriginApi();
        // 使用 MessageChannel 进行消息发送
        syncMessageByMessageChannel();
    }
}
