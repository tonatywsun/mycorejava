package com.yang.jdk1_8.interfacetest.method;

import com.yang.jdk1_8.interfacetest.method.TestInterface;

/**
 * @author yangyang
 * @date 2019/8/21 13:30
 */
public class TestInterfaceImpl implements TestInterface {
    static void teststatic() {
        System.out.println("impl teststatic");
    }

    @Override
    public void test() {
        System.out.println("impl test");
    }

    /*@Override
    public void testdefault() {
        System.out.println("impl default");
    }*/
}
