package com.mall.order.service.impl;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenlufeng
 * @date 2022/2/26
 */
@Slf4j
public class OrderServiceImpl3BlockHandlerClass {
    public static String blockHandler(BlockException ex){
        log.error("{}",ex);
        return "接口被限流或者降级了...";
    }
}
