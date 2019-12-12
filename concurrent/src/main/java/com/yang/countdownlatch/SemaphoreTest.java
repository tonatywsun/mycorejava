package com.yang.countdownlatch;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: Semaphore使用测试
 * Semaphore是一种基于计数的信号量。它可以设定一个阈值，基于此，多个线程竞争获取许可信号，做自己的申请后归还，超过阈值后，线程申请许可信号将会被阻塞。
 * 简单的说就是最多允许阈值个线程同时运行，多余的阻塞
 * @Author: tona.sun
 * @Date: 2019/11/27 17:57
 */
public class SemaphoreTest {
    private static final int THREAD_COUNT = 5;

    @Test
    public void test() throws InterruptedException {
        ExecutorService threadPool = new ThreadPoolExecutor(THREAD_COUNT, THREAD_COUNT,
                1L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100));
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new MyThread());
        }
        Thread.sleep(20000);
        threadPool.shutdown();
    }
}

class MyThread implements Runnable {
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static Semaphore s = new Semaphore(0);//初始为0必须先release，不然只能允许同时0个通过，每 多（release多于acquire） release一次相当于permits+1

    @Override
    public void run() {
        try {
            System.out.println("before---" + atomicInteger.get());
            if (atomicInteger.incrementAndGet() == 5) {
                s.release();//每多release一次相当于permits+1
                s.release();
            }
            //多余的暂停 最多有permits个线程同时在acquire和release之间运行
            s.acquire();
            System.out.println("save data");
            Thread.sleep(3000);
            s.release();
        } catch (InterruptedException e) {
        }
    }
}

