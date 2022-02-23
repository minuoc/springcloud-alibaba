package com.mall.order.service;

import com.mall.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author chenlufeng
 * @date 2022/2/23
 */
@FeignClient("service-product") // 声明调用的提供者的name
public interface ProductService {
    //指定调用提供者的哪个方法
    //@FeignClient  + @GetMapping 就是一个完整的请求路径 http://service-product/{pid}

    @GetMapping(value = "/product/{pid}")
    Product findByPid(@PathVariable("pid")Integer pid);

}
