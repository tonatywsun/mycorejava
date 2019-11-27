package com.yang.atomic;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: AtomicInteger原子类, 可以用来原子加减
 * @Author: tona.sun
 * @Date: 2019/11/26 14:07
 */
public class AtomicIntegerTest extends Thread {
    //正常int类
    //public static int k = 0;
    //原子类
    public static AtomicInteger atomicInteger = new AtomicInteger();
    //嘻嘻 用个栅栏玩一玩
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(100, () -> System.out.println("一百个线程到齐，开闸！！！"));

    @Override
    public void run() {
        try {
            //拦住
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
        //等待子线程执行完毕
        Thread.sleep(10000);
        //无法达到预期值
        //System.out.println(k);
        //预期值
        System.out.println(atomicInteger.get());
    }
}
