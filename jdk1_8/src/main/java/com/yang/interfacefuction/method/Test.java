package com.yang.interfacefuction.method;

/**
 * @author yangyang
 * @date 2019/8/21 13:32
 */
public class Test {
    public static void main(String[] args) {
        TestInterface testInterface =new TestInterfaceImpl();
        TestInterface.teststatic();
        TestInterfaceImpl.teststatic();
        testInterface.testdefault();
        testInterface.test();
    }
}
