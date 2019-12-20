package com.yang.proxy.staticproxy.proxy.polymerization;

import com.yang.proxy.service.OrderService;

/**
 * @Description: 聚合方式实现代理
 * @Author: tona.sun
 * @Date: 2019/12/16 15:25
 */
public class OrderServiceProxyP {
    OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public String order(String s) throws Exception{
        System.out.println("staticproxy polymerization proxy!");
        return orderService.order(s);
    }
}
