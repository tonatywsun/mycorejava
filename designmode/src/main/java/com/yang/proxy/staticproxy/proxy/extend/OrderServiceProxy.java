package com.yang.proxy.staticproxy.proxy.extend;

import com.yang.proxy.service.impl.OrderServiceImpl;

/**
 * @Description: 继承方式实现代理
 * @Author: tona.sun
 * @Date: 2019/12/16 15:25
 */
public class OrderServiceProxy extends OrderServiceImpl {
    @Override
    public String order(String s) {
        System.out.println("staticproxy extend proxy!");
        return super.order(s);
    }
}
