package com.mall.order.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenlufeng
 * @date 2022/2/25
 *
 * 自己构建 CommonFilter 实例
 */
//@Configuration
//public class FilterContextConfig {
//
////    @Bean
////    public FilterRegistrationBean sentinelFilterRegistration() {
////        FilterRegistrationBean registration = new FilterRegistrationBean();
////        registration.setFilter(new CommonFilter());
////        registration.addUrlPatterns("/*");
////
////        //入库资源关闭聚合
////        registration.addInitParameter("WEB_CONTEXT_UNIFY","false");
////        registration.setName("sentinelFilter");
////        registration.setOrder(1);
////        return registration;
////    }
//
//}
