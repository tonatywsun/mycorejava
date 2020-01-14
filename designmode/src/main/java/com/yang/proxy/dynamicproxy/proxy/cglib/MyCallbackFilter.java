package com.yang.proxy.dynamicproxy.proxy.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * @Description: TODO
 * @Author: tona.sun
 * @Date: 2020/01/14 13:46
 */
public class MyCallbackFilter implements CallbackFilter {
    /**
     * 根据返回所以去private Callback[] callbacks;中寻找匹配的Callback
     * 所以要设置callbacks的下标要满足
     */
    @Override
    public int accept(Method method) {
        if (method.getName().equals("method1")) {
            System.out.println("filter method1 ==0");
            return 0;
        } else if (method.getName().equals("method2")) {
            System.out.println("filter method2 ==1");
            return 1;
        } else if (method.getName().equals("method3")) {
            System.out.println("filter method3 ==2");
            return 2;
        }
        return 0;
    }
}
