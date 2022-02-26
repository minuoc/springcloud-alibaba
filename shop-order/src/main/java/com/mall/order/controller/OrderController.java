package com.mall.order.controller;

import com.alibaba.fastjson.JSON;
import com.mall.domain.Order;
import com.mall.domain.Product;
import com.mall.order.service.OrderService;
import com.mall.order.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * @author chenlufeng
 * @date 2022/2/23
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProductService productService;

    //下单--fegin
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info(">>客户下单，这时候要调用商品微服务查询商品信息");

        //从nacos中获取服务地址
//        ServiceInstance serviceInstance = discoveryClient.getInstances("service-product").get(0);
//        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("service-product");
//        int index = new Random().nextInt(serviceInstanceList.size());
//
//        ServiceInstance serviceInstance = serviceInstanceList.get(index);
//
//        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
//        log.info(">>从nacos中获取到的微服务地址为:" + url);

        //直接使用微服务名字, 从nacos中获取服务地址
//        String url = "service-product";
//        Product product = restTemplate.getForObject("http://" + url + "/product/" + pid, Product.class);

        //使用feign 调用
        Product product = productService.findByPid(pid);
        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
        if (product.getPid() == -1){
            Order order = new Order();
            order.setPname("下单失败");
            return order;
        }
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());

        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.save(order);

        log.info("创建订单成功,订单信息为{}",JSON.toJSONString(order));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return order;
    }


}
