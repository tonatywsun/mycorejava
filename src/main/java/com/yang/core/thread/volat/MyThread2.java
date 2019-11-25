package com.yang.core.thread.volat;

/**
 * 个人观点volatile无法保证对变量原子性
 * @date 2018年3月26日 下午3:02:21 
 * @author tonasun
 */
public class MyThread2 extends Thread {
    volatile public static int count;

    private /*synchronized*/ static void addCount() {
        for (int i = 0; i < 100; i++) {
            count = i;
        }
        
        if (count != 99) {
            System.out.println(Thread.currentThread() + "count=" + count);
        }

    }

    @Override
    public void run() {
        addCount();
    }
}
