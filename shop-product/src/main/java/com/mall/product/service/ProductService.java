package com.mall.product.service;

import com.mall.domain.Product;

/**
 * @author chenlufeng
 * @date 2022/2/23
 */
public interface ProductService {
    Product findById(Integer pid);
}
