package com.yang.proxy.dynamicproxy.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * JDK动态代理
 * 被代理类必须实现接口
 *
 * @author tona.sun
 * @date 2019/8/17 17:11
 */
public class MyJdkInvocationHandler implements InvocationHandler {
    private Object tag;

    public MyJdkInvocationHandler(Object tag) {
        this.tag = tag;
    }

    //被代理方法其实执行的是这个方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        System.out.println("invoke 对被代理类方法增强");
        try {
            return method.invoke(tag, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
