package com.yang.proxy.staticproxy;

import com.yang.proxy.service.OrderService;
import com.yang.proxy.service.impl.OrderServiceImpl;
import com.yang.proxy.staticproxy.proxy.extend.OrderServiceProxy;
import com.yang.proxy.staticproxy.proxy.polymerization.OrderServiceProxyP;

/**
 * @author tona.sun
 * @date 2019/8/17 16:55
 */
public class Client {
    public static void main(String[] args) {
        OrderService proxy = new OrderServiceProxy();
        System.out.println("proxy1-" + proxy.order("main"));

        OrderServiceProxyP proxy2 = new OrderServiceProxyP();
        proxy2.setOrderService(new OrderServiceImpl());
        System.out.println("proxy2-" + proxy2.order("main"));
    }
}
