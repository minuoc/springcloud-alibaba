package com.mall.order.dao;

import com.mall.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chenlufeng
 * @date 2022/2/23
 */
public interface OrderDao extends JpaRepository<Order,Integer> {


}
