package com.yang.proxy.dynamicproxy.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * JDK动态代理
 * 被代理类必须实现接口,因为Java动态代理只能够对接口进行代理，不能对普通的类进行代理（因为所有生成的代理类的父类为Proxy，Java类继承机制不允许多重继承）
 * <p>
 * 就是你给我一个接口，我替你生成一个代理实现类。
 * 为什么要传一个tag呢？完全可以不传的，只是因为你想使用它的方法去实现你的逻辑。
 * 我们可以拿到returnType,自定义的返回一个同类型的值，但是你这创建这个值的逻辑（invoke中的逻辑，只有一个returnType你只能创建一个返回对象，但是你得set属性吧，这个set属性的逻辑）是定制化的，只能适用某个接口中的某一个方法的实现逻辑，不适用这个接口中的所有方法，更不适用所有接口的所有方法。
 * 所以最好的办法就是我们拿到接口的一个已有的实现类去执行它里面的每一个对应接口的实现方法
 * </p>
 * Java动态代理使用Java原生的反射API进行操作，在生成类上比较高效
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
        System.out.println("invoke proxy:" + proxy);
        /*Class<?> returnType = method.getReturnType();
        if ("void".equals(returnType.getName())) {
            return null;
        }*/
        try {
            //执行的是tag中的method,因为method是用tag获取的，所以只能用tag去执行
            //后来我改成了method用接口去获取，这样就能用proxy去执行了，然后不出所料的发生了内存溢出（循环调用，死循环了）
            //Object invoke = method.invoke(proxy, args);
            Object invoke = method.invoke(tag, args);
            return invoke;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (Throwable e){
            e.printStackTrace();
        }
        return null;
    }
}
