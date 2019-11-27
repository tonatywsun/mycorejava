package com.yang.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: (计数器)CountDownLatch用法
 * 假如有这样一个需求，当我们需要解析一个Excel里多个sheet的数据时，可以考虑使用多线程，每个线程解析一个sheet里的数据，等到所有的sheet都解析完之后，程序需要提示解析完成。
 * @Author: tona.sun
 * @Date: 2019/11/27 16:45
 */
public class CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                    System.out.println(Thread.currentThread().getId() + "执行完毕");
                    //每次减一，也可以一个线程里面多次减
                    c.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        //阻塞，等待count减为0时释放
        c.await();
        System.out.println("主线程执行完毕");
    }

}
