package com.yang.core.thread.volat;

import org.junit.Test;

public class VolatileTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        Thread.sleep(1000);
        thread.setRunning(false);
        System.out.println("已经赋值为false");
    }

    @Test
    public void test1() {
        MyThread2[] mythreadArray = new MyThread2[1000];
        for (int i = 0; i < 1000; i++) {
            mythreadArray[i] = new MyThread2();
        }

        for (int i = 0; i < 1000; i++) {
            mythreadArray[i].start();
        }
    }
}
