package com.mall.order.service.impl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author chenlufeng
 * @date 2022/2/26
 */
@Slf4j
public class OrderServiceImpl3FallbackClass {
    public static String fallback(Throwable throwable) {
        log.error("{}",throwable);
        return "接口发生异常了...";
    }
}
