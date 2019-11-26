package com.yang.core.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: AtomicInteger原子类
 * @Author: tona.sun
 * @Date: 2019/11/26 14:07
 */
public class AtomicIntegerTest extends Thread {
    //public static int k = 0;
    public static AtomicInteger atomicInteger = new AtomicInteger();
    //嘻嘻 用个栅栏玩一玩
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(100, () -> {
        System.out.println("一百个线程到齐，开闸！！！");
    });

    @Override
    public void run() {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        //原子+1 and返回
        for (int j = 0; j < 10000; j++) {
            int i = atomicInteger.incrementAndGet();
            //int i = ++k;
            System.out.println(Thread.currentThread().getId() + "---" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new AtomicIntegerTest().start();
        }
        Thread.sleep(10000);
        //System.out.println(k);
        System.out.println(atomicInteger.get());
    }
}
