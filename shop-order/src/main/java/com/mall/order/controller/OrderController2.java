package com.mall.order.controller;

import com.alibaba.fastjson.JSON;
import com.mall.domain.Order;
import com.mall.domain.Product;
import com.mall.order.service.OrderService;
import com.mall.order.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenlufeng
 * @date 2022/2/23
 */
@RestController
@Slf4j
public class OrderController2 {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid")Integer pid) {
        log.info(">>接收到{}号商品的下单请求,接下来调用商品微服务查询商品信息",pid);

        //使用feign 调用
        Product product = productService.findByPid(pid);
        log.info("查询到{}号商品信息,内容是:" ,pid, JSON.toJSONString(product));

        //模拟一次网络掩饰
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());

        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
//        orderService.save(order);

        log.info("创建订单成功,订单信息为{}",JSON.toJSONString(order));

        return order;
    }

    @RequestMapping("/order/message")
    public String message(){
        return "高并发下的问题测试";
    }


}
