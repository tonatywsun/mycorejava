package com.yang.proxy.staticproxy.proxy;

import com.yang.proxy.service.OrderService;

/**
 * @author yangyang
 * @date 2019/8/17 16:59
 */
public class OrderServiceProxy implements OrderService {
    private OrderService orderService;

    public OrderServiceProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String order(String s) {
        System.out.println("OrderServiceProxy 对被代理类方法增强" + s);
        orderService.order("proxy");
        return s;
    }
}
