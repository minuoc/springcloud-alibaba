package com.mall.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mall.order.service.impl.OrderServiceImpl3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenlufeng
 * @date 2022/2/24
 */
@RestController
@Slf4j
public class OrderController3 {


    @Autowired
    private OrderServiceImpl3 orderServiceImpl3;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @SentinelResource("hello")
    public String hello() {
        return "Hello hello";
    }

    @RequestMapping("/order/message1")
    public String message1(){
        return "message1";
    }

//    int i = 0;
    @RequestMapping("/order/message2")
    public String message2(){
        orderServiceImpl3.message();
        return "message2";
    }



    @RequestMapping("/order/message3")
    @SentinelResource("message3")
    public String message3(String name,Integer age){
        return name + age;
    }
}
