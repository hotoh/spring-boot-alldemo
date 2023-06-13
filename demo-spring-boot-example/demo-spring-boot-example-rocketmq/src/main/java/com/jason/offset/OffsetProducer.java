package com.jason.offset;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.jason.message.Demo;
import com.jason.message.RqMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 延时消息队列
 *
 * @author jason
 * @date 2019/8/21
 */

@Component
@Slf4j
public class OffsetProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void send() {
        String message = "offset message:  ";
        for (int i = 0; i < 10; i++) {
            rocketMQTemplate.syncSendOrderly("topic-offset", message + i, "offset_orderly_key");
        }
    }

    public void newSend() {
        RqMessage<List<Demo>> stringMessage = new RqMessage<>();
        stringMessage.setId(22222L);

        ArrayList<Demo> objects = new ArrayList<>();
        Demo demo = new Demo();
        demo.setId(123);
        demo.setName("商品名称");
        demo.setPrice(20);

        Demo demo1 = new Demo();
        demo1.setId(456);
        demo1.setName("商品名称456");
        demo1.setPrice(654);

        objects.add(demo);
        objects.add(demo1);
        stringMessage.setData(objects);
        rocketMQTemplate.syncSend("topic-offset-by-jason",stringMessage,3000L,3).getSendStatus();
    }

    public void newSend1() {
        ArrayList<Demo> objects = new ArrayList<>();
        Demo demo = new Demo();
        demo.setId(123);
        demo.setName("商品名称");
        demo.setPrice(20);

        Demo demo1 = new Demo();
        demo1.setId(456);
        demo1.setName("商品名称456");
        demo1.setPrice(654);

        objects.add(demo);
        objects.add(demo1);

        //数组转json
        JSONArray objects1 = JSONUtil.parseArray(objects);
        String string = objects1.toString();

        Message message = new Message();
        message.setTopic("topic-offset-by-jason");
        message.setBody(string.getBytes());

//        rocketMQTemplate.syncSend("topic-offset-by-jason",message,3000L,3).getSendStatus();


    }





    /***
     * jason写的延时消费demo
     */
    public void sendByjason() throws Exception {
        Message message = new Message();
        //生产者
        DefaultMQProducer producer = new DefaultMQProducer("topic-offset-by-jason-product");
        producer.setNamesrvAddr("116.205.224.23:9876");
        producer.start();
        for(int i = 0;i<5;i++){
            message.setTopic("topic-offset-by-jason");
            message.setBody(("我是延迟消费啊啊" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            message.setDelayTimeLevel(3);
            producer.send(message);
        }
        //关闭生产者
        producer.shutdown();
    }




    public static String result(byte[] decrypt) {
        try {
            String result = new String(decrypt, "UTF-8");
            return result;
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] bytes = ("我是延迟消费啊啊").getBytes(RemotingHelper.DEFAULT_CHARSET);
        String result = result(bytes);
        System.out.println("输出：" + result);
    }


}
