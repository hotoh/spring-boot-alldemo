package com.jason.order;

import com.jason.message.MessageObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author jason
 * @date 2023/8/21
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = "topic-orderly",
        consumerGroup = "orderly-consumer-group", consumeMode = ConsumeMode.ORDERLY
)

public class OrderConsumer implements RocketMQListener<MessageObject> {
    int sumId1 = 0;
    int sumId2 = 0;
    @Override
    public void onMessage(MessageObject messageObject) {
        if(messageObject.getId().equals("10086")){
            sumId1 = sumId1+Integer.parseInt((String) messageObject.getContent());
        }
        else{
            sumId2 = sumId2+Integer.parseInt((String) messageObject.getContent());
        }
        System.out.println("开始消费");
        log.info("========{}=======", sumId1);
        log.info("========{}=======", sumId2);
        System.out.println("消费结束");
    }

}
