package com.jason.run;


import com.jason.messagemodel.MessageModelProducer;
import com.jason.offset.OffsetProducer;
import com.jason.order.OrderProducer;
import com.jason.simple.RocketmqProducer;
import com.jason.tags.TagProducer;
import com.jason.transaction.TransactionProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author jason
 * @date 2023/8/21
 */
@Component
public class Runner implements CommandLineRunner {

    @Resource
    private RocketmqProducer rocketmqProducer;
    @Resource
    private TransactionProducer transactionProducer;
    @Resource
    private OrderProducer orderProducer;
    @Resource
    private TagProducer tagProducer;
    @Resource
    private OffsetProducer offsetProducer;
    @Resource
    private MessageModelProducer messageModelProducer;

    @Override
    public void run(String... args) throws Exception {
        //同步
//        rocketmqProducer.sync();
        //单向发送
//        rocketmqProducer.oneWay();
        //异步
//        rocketmqProducer.async();
        //订单  顺序消息
//        orderProducer.sendSyncOrderly();
        //顺序消息测这个 1 2 3 4
//        orderProducer.testSendSyncOrderly1();
//        orderProducer.testSendSyncOrderly2();
//        orderProducer.testSendSyncOrderly3();
//        orderProducer.testSendSyncOrderly4();
        //事务消息
//        tagProducer.sendTagsMessage();
        //延迟消息
//        offsetProducer.send();
//        offsetProducer.sendByjason();
        offsetProducer.newSend1();

        //根据tag消费消息
//        tagProducer.sendTagsMessage();

        //事务处理
//        transactionProducer.produce();
//        messageModelProducer.send();
    }


}
