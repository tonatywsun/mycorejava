package com.yang.core.thread.sync;

public class MySyncClass {
    public synchronized void service1() {
        System.out.println("service1");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void service2() {
        System.out.println("service2");
    }
}
