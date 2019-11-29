package com.yang.singleton;

/**
 * 懒汉式
 *
 * @author tona.sun
 * @date 2019/8/24 14:24
 */
public class Lazy {
    private volatile static Lazy singleton;

    private Lazy() {
    }

    public static Lazy getSingleton() {
        if (singleton == null) {
            synchronized (Lazy.class) {
                if (singleton == null) {
                    singleton = new Lazy();
                }
            }
        }
        return singleton;
    }

    public void doSomething() {
        System.out.println("i will do");
    }
}
