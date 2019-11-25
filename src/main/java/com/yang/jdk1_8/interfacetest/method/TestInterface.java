package com.yang.jdk1_8.interfacetest.method;

/**
 * @author yangyang
 * @date 2019/8/21 13:29
 */
public interface TestInterface {
    default public void testdefault() {
        System.out.println("interface default");
    }

    static void teststatic() {
        System.out.println("interface static");
    }

    public void test();
}
