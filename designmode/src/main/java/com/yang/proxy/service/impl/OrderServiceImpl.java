package com.yang.proxy.service.impl;

import com.yang.proxy.service.OrderService;

/**
 * 需要被代理的对象
 *
 * @author tona.sun
 * @date 2019/8/17 16:57
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public String order(String s) {
        System.out.println("OrderServiceImpl order s");
        return s;
    }

    @Override
    public void order(String s, Integer i) {
        System.out.println("OrderServiceImpl order String s, Integer i");
    }

    @Override
    public void order() {
        System.out.println("OrderServiceImpl order");
    }


}
