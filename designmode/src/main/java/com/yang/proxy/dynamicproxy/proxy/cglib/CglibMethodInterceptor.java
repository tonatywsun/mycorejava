package com.yang.proxy.dynamicproxy.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 * 被代理类不需要实现接口
 * @author yangyang
 * @date 2019/8/17 19:35
 */
public class CglibMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("intercept 对被代理类方法增强");
        return methodProxy.invokeSuper(o, objects);
    }
}
