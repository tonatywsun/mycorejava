package com.yang.core.thread.pool;

public class MyThread implements Runnable {
    public MyThread() {
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
