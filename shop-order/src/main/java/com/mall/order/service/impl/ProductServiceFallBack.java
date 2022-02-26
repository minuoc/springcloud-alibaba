package com.mall.order.service.impl;

import com.mall.domain.Product;
import com.mall.order.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author chenlufeng
 * @date 2022/2/26
 */
//容错类要求实现被容错的接口，并为每个方法实现容错方案
@Component
@Slf4j
public class ProductServiceFallBack implements ProductService {
    @Override
    public Product findByPid(Integer pid) {
        Product product = new Product();
        product.setPid(-1);
        return product;
    }
}
