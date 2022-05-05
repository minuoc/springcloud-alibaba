package com.shop;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * @author chenlufeng
 * @date 2022/5/4
 */
public class RocketMQSendTest {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("myproducer-producer");

        producer.setNamesrvAddr("192.168.163.129:9876");

        producer.start();

        Message message = new Message("myTopic","myTag","RocketMQ ssss".getBytes(StandardCharsets.UTF_8));


        SendResult sendResult = producer.send(message,10000);

        System.out.println(sendResult);

        producer.shutdown();



    }
}
