package com.mall.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chenlufeng
 * @date 2022/2/25
 */
@Service
@Slf4j
public class OrderServiceImpl3 {
    int i = 0;
    @SentinelResource(value = "message",
        blockHandler = "blockHandler",
            fallback = "fallback"
    )
    public String message() {
        i++;
        if (i % 3 == 0){
            throw new RuntimeException();
        }
        return "message";
    }

    //blockException 时进入的方法
    public String blockHandler(BlockException ex){
        log.error("{}",ex);
        return "接口被限流或者降级了...";
    }

    public String fallback(Throwable throwable) {
        log.error("{}",throwable);
        return "接口发生异常了...";
    }
}
