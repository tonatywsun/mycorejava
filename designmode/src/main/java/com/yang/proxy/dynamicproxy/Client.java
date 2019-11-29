package com.yang.proxy.dynamicproxy;


import com.yang.proxy.dynamicproxy.proxy.cglib.CglibMethodInterceptor;
import com.yang.proxy.service.impl.OrderServiceImpl;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author tona.sun
 * @date 2019/8/17 16:55
 */
public class Client{
    public static void main(String[] args) {
        //在main方法中调用此方法，这样就会把生成的代理类Class文件保存在本地磁盘上，然后再反编译可以得到代理类的源码。
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        /*JdkInvocationHandler jdkInvocationHandler = new JdkInvocationHandler(new OrderServiceImpl());
        OrderService proxy = jdkInvocationHandler.getProxy();
        proxy.order("main");*/

        CglibMethodInterceptor cglibMethodInterceptor = new CglibMethodInterceptor();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OrderServiceImpl.class);
        enhancer.setCallback(cglibMethodInterceptor);
        OrderServiceImpl orderService = (OrderServiceImpl)enhancer.create();
        orderService.order("cglib main");
    }
}
