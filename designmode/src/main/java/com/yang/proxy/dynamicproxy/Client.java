package com.yang.proxy.dynamicproxy;


import com.yang.proxy.dynamicproxy.proxy.jdk.MyJdkInvocationHandler;
import com.yang.proxy.service.OrderService;
import com.yang.proxy.service.impl.OrderServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @author tona.sun
 * @date 2019/8/17 16:55
 */
public class Client {
    public static void main(String[] args) throws Exception {
        //在main方法中调用此方法，这样就会把生成的代理类Class文件保存在本地磁盘上，然后再反编译可以得到代理类的源码。
        //在D:\JetBrains\IntelliJ IDEA 2019.2.3\myworkspace\mycorejava\com\sun\proxy中可以看到源码
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        //目标对象
        OrderServiceImpl tagert = new OrderServiceImpl();
        MyJdkInvocationHandler jdkInvocationHandler = new MyJdkInvocationHandler(tagert);
        OrderService proxy = (OrderService) Proxy.newProxyInstance(Client.class.getClassLoader(), tagert.getClass().getInterfaces(), jdkInvocationHandler);
        proxy.order();
        /*OrderServiceImpl tagert = new OrderServiceImpl();
        MyJdkInvocationHandler jdkInvocationHandler = new MyJdkInvocationHandler(tagert);
        OrderService proxy = (OrderService) MyProxyUtil.getProxy(tagert.getClass().getClassLoader(),tagert.getClass().getInterfaces(), jdkInvocationHandler);
        proxy.order("ss");*/
        /*Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OrderServiceDao.class);
        Callback noopCb = NoOp.INSTANCE;
        CglibMethodInterceptor cglibMethodInterceptor = new CglibMethodInterceptor();
        MyFixedValue myFixedValue = new MyFixedValue();*/
        //enhancer.setCallbacks(new Callback[]{noopCb, cglibMethodInterceptor, myFixedValue});
        /*
            过滤出对应的Callback,逻辑自己写
         */
       /* enhancer.setCallbackFilter(new MyCallbackFilter());
        enhancer.setCallbackTypes(new Class[]{NoOp.class, CglibMethodInterceptor.class, MyFixedValue.class});
        //OrderServiceDao orderService = (OrderServiceDao) enhancer.create();
        Class aClass = enhancer.createClass();*/
        /*enhancer.setInterfaces(new Class[]{OrderService.class});
        OrderService orderServiceDao = (OrderService) enhancer.create();*/
        /**
         * 对需要延迟加载的对象添加代理，在获取该对象属性时先通过代理类回调方法进行对象初始化。
         * 在不需要加载该对象时，只要不去获取该对象内属性，该对象就不会被初始化了（在CGLib的实现中只要去访问该对象内属性的getter方法，
         * 就会自动触发代理类回调）。
         */
        /*
            只有第一次懒加载
         */
        /*OrderServiceDao orderService2 = (OrderServiceDao) Enhancer.create(OrderServiceDao.class, (LazyLoader) () -> {
            System.out.println("before lazyLoader...");
            OrderServiceDao propertyBean = new OrderServiceDao();
            System.out.println("after lazyLoader...");
            return propertyBean;
        });
        *//*
            每次都懒加载
         *//*
        OrderServiceDao orderService3 = (OrderServiceDao) Enhancer.create(OrderServiceDao.class, (Dispatcher) () -> {
            System.out.println("before Dispatcher...");
            OrderServiceDao propertyBean = new OrderServiceDao();
            System.out.println("after Dispatcher...");
            return propertyBean;
        });*/
        //System.out.println(orderService);
        /*System.out.println(orderService.method1());
        System.out.println(orderService.method2());
        System.out.println(orderService.method3());
        System.out.println(orderService2.method1());
        System.out.println(orderService2.method2());
        System.out.println(orderService2.method3());
        System.out.println(orderService3.method1());
        System.out.println(orderService3.method2());
        System.out.println(orderService3.method3());*/
    }
}
