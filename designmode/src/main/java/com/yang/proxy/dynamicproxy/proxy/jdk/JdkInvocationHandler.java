package com.yang.proxy.dynamicproxy.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 * 被代理类必须实现接口
 * @author tona.sun
 * @date 2019/8/17 17:11
 */
public class JdkInvocationHandler implements InvocationHandler {
    private Object tag;

    public JdkInvocationHandler(Object tag) {
        this.tag = tag;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke 对被代理类方法增强");
        return method.invoke(tag, args);
    }

    /**
     * 获取代理对象
     * @return:T
     * @Author:yang
     * @Date: 2019/8/17 17:54
     */
    public <T> T getProxy() {
        /**
         *jdk的动态代理调用了Proxy.newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h) 方法。
         *通过该方法生成字节码,动态的创建了一个代理类
         */
        return (T) Proxy.newProxyInstance(tag.getClass().getClassLoader(), tag.getClass().getInterfaces(), this);
    }
}
