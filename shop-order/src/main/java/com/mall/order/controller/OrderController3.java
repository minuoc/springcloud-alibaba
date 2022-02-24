package com.mall.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenlufeng
 * @date 2022/2/24
 */
@RestController
@Slf4j
public class OrderController3 {

    @SentinelResource("/resource")
    public String hello(){
        return "Hello";
    }

    @RequestMapping("/order/message1")
    public String message1(){
        return "message1";
    }

    @RequestMapping("/order/message2")
    public String message2(){
        return "message2";
    }
}
