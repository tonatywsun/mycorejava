package com.yang.core.thread.pool;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: MyTest
 *
 * @author tonasun
 * @Description: TODO
 * @date 2017年9月27日 上午11:23:27
 */
@SuppressWarnings("all")
public class ExecutorsTest {

    /**
     * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可复用，则新建线程。
     * 线程池为0x7fffffff，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程，线程空闲时间为60s就会被回收
     * 无界限队列
     */
    @Test
    public void test1() {
        //使用了SynchronousQueue，这个暂时不了解
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            cachedThreadPool.execute(() -> System.out.println(Thread.currentThread()));
        }
    }

    /**
     * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     * 核心线程数==最大线程数 无界队列 keepalivetime==0
     */
    @Test
    public void test2() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(() -> {
                try {
                    System.out.println(Thread.currentThread());
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description : 创建一个定长线程池，支持定时及周期性任务执行
     * @author : tona.sun
     * @date : 2019/11/22 16:07
     */
    @Test
    public void test3() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        // 延迟三秒执行
        //scheduledThreadPool.schedule(() -> System.out.println(Thread.currentThread()), 3, TimeUnit.SECONDS);
        // 延迟三秒后每五秒执行一次
        scheduledThreadPool.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread()), 3, 5, TimeUnit.SECONDS);
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description : 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     * @author : tona.sun
     * @date : 2019/11/22 16:13
     */
    @Test
    public void test4() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread() + "---" + index);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        ExecutorService singleThreadExecutor = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            singleThreadExecutor.execute(() -> {
                try {
                    while (true) {
                        System.out.println(index);
                        Thread.sleep(10 * 1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test6() throws InterruptedException, ExecutionException {
        Callable<String> callable = () -> "个人博客：sunyangyang.com";
        FutureTask<String> task = new FutureTask<String>(callable);

        Thread t = new Thread(task);
        t.start(); // 启动线程
        System.out.println(task.get());
        //task.cancel(true); // 取消线程
    }
}
