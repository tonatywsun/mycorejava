package com.yang.singleton;

/**
 * 饿汉式
 *
 * @author tona.sun
 * @date 2019/8/24 13:40
 */
public class Hungry {
    private static Hungry instance = new Hungry();

    private Hungry() {
    }

    public static Hungry getInstance() {
        return instance;
    }

    public void doSomething() {
        System.out.println("i will do");
    }
}
