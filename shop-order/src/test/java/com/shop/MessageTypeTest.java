package com.shop;

import com.mall.order.OrderApplication;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenlufeng
 * @date 2022/5/5
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class MessageTypeTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //同步消息
    @Test
    public void testSyncSend(){

        SendResult sendResult = rocketMQTemplate.syncSend("test-topic-1","这是一条同步消息");
        System.out.println(sendResult);

    }

    //异步消息
    @Test
    public void testAsyncSend() throws InterruptedException {
        rocketMQTemplate.asyncSend("test-topic-1", "这是一条异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println(throwable);
            }
        });
        //让线程不要终止
        Thread.sleep(3000000);
    }

    @Test
    public void testOneWay(){
        rocketMQTemplate.sendOneWay("test-topic-1","这是一条单向消息");
    }

}
