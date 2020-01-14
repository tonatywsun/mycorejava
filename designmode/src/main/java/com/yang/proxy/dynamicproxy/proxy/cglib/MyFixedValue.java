package com.yang.proxy.dynamicproxy.proxy.cglib;

import net.sf.cglib.proxy.FixedValue;

/**
 * @Description: 返回固定值的Callback，不执行代理类的方法，直接返回固定值
 * @Author: tona.sun
 * @Date: 2020/01/14 14:00
 */
public class MyFixedValue implements FixedValue {
    @Override
    public Object loadObject() throws Exception {
        System.out.println("MyFixedValue loadObject");
        return 888;
    }
}
