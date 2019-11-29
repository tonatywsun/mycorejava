package com.yang.proxy.service.impl;

import com.yang.proxy.service.OrderService;

/**
 * @author tona.sun
 * @date 2019/8/17 16:57
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public String order(String s) {
        System.out.println("OrderServiceImpl order:" + s);
        return s;
    }

}
