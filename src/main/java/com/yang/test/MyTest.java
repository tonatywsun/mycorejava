package com.yang.test;

import java.util.concurrent.LinkedBlockingQueue;

public interface/* class */ MyTest {
    default void test1() {
        System.out.println(Math.round(-1.5));
    }

    static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue a = null;
        a.put(new Object());
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        MyTest m = new MyTest() {
        };
        m.test1();
    }
}
