package com.yang.proxy.staticproxy;

import com.yang.proxy.service.OrderService;
import com.yang.proxy.service.impl.OrderServiceImpl;
import com.yang.proxy.staticproxy.proxy.OrderServiceProxy;

/**
 * @author tona.sun
 * @date 2019/8/17 16:55
 */
public class Client {
    public static void main(String[] args) {
        OrderService proxy = new OrderServiceProxy(new OrderServiceImpl());
        System.out.println("main-" + proxy.order("main"));
    }
}
