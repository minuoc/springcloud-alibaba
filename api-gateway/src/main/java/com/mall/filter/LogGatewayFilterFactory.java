package com.mall.filter;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author chenlufeng
 * @date 2022/4/22
 */
//@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    public LogGatewayFilterFactory(){
        super(LogGatewayFilterFactory.Config.class);
    }


    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog","cacheLog");
    }

    @Override
    public GatewayFilter apply(LogGatewayFilterFactory.Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if (config.isCacheLog()) {
                    System.out.println("cacheLog已经开启了....");
                }
                if (config.isConsoleLog()) {
                    System.out.println("consoleLog已经开启了....");
                }
                return chain.filter(exchange);
            }

        };
    }

    @Data
    @NoArgsConstructor
    public static class Config {
        private boolean consoleLog;
        private boolean cacheLog;
    }

}
