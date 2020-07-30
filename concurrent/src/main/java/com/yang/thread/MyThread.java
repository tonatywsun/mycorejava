package com.yang.thread;

/**
 * @Description: TODO
 * @Author: tona.sun
 * @Date: 2020/04/16 15:34
 */
public class MyThread extends Thread {
    public MyThread() {
        super();
    }

    @Override
    public void run() {
        System.out.println("my thread");
    }
}
