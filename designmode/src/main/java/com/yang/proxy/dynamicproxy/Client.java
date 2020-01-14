package com.yang.proxy.dynamicproxy;


import com.yang.proxy.dynamicproxy.proxy.jdk.MyJdkInvocationHandler;
import com.yang.proxy.dynamicproxy.proxy.jdk.util.MyProxyUtil;
import com.yang.proxy.service.OrderService;
import com.yang.proxy.service.impl.OrderServiceImpl;

/**
 * @author tona.sun
 * @date 2019/8/17 16:55
 */
public class Client {
    public static void main(String[] args) throws Exception {
        //在main方法中调用此方法，这样就会把生成的代理类Class文件保存在本地磁盘上，然后再反编译可以得到代理类的源码。
        //在D:\JetBrains\IntelliJ IDEA 2019.2.3\myworkspace\mycorejava\com\sun\proxy中可以看到源码
        //System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        //目标对象
        /*OrderServiceImpl tagert = new OrderServiceImpl();
        MyJdkInvocationHandler jdkInvocationHandler = new MyJdkInvocationHandler(tagert);
        OrderService proxy = (OrderService) Proxy.newProxyInstance(Client.class.getClassLoader(), tagert.getClass().getInterfaces(), jdkInvocationHandler);
        proxy.order();*/
        OrderServiceImpl tagert = new OrderServiceImpl();
        MyJdkInvocationHandler jdkInvocationHandler = new MyJdkInvocationHandler(tagert);
        OrderService proxy = (OrderService) MyProxyUtil.getProxy(tagert.getClass().getClassLoader(),tagert.getClass().getInterfaces(), jdkInvocationHandler);
        proxy.order("ss");
        /*Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OrderServiceImpl.class);
        enhancer.setCallback(new CglibMethodInterceptor());
        OrderServiceImpl orderService = (OrderServiceImpl)enhancer.create();
        //System.out.println(orderService);
        orderService.order("cglib main");*/
    }
}
