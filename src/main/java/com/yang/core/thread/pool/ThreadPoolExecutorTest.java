package com.yang.core.thread.pool;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 线程池ThreadPoolExecutor源码学习和测试
 * @Author: tona.sun
 * @Date: 2019/11/25 10:02
 */
public class ThreadPoolExecutorTest {
    @Test
    public void test() throws Exception {
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(3);
        /*
         *corePoolSize:线程池中的核心线程数，空闲的线程也不会回收，除非把allowCoreThreadTimeOut设置为true，这时核心线程超过存活时间才会被回收
         *maximumPoolSize:线程池中可以同时运行的最大线程数,当使用无界的阻塞队列的时候队列是塞不满的，所以不会创建核心线程数之外的线程去运行，此值就是无效的
         *keepAliveTime:当线程池中创建的线程超过了核心线程数的时候，这些多余的空闲线程在结束之前等待新的任务最大的存活时间
         *unit:keepAliveTime的时间单位，可以是纳秒，微秒，毫秒，秒，分钟，小时，天
         *workQueue:存放任务的队列，只有当线程数>核心线程数，才会把其他的任务放入queue,当queue放满之后再往其中塞任务会新建线程去执行这些无法塞入的任务，
         *          新建线程数加核心线程数达到最大线程数时,此时队列满了无法塞入，线程数已达最大无法创建，最执行拒绝策略（具体的实现）
         */
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(2, 4, 2000, TimeUnit.MILLISECONDS, blockingQueue);
        //threadPoolExecutor.allowCoreThreadTimeOut(true);
        //核心线程执行
        threadPoolExecutor.execute(new MyRunnable("1"));
        //核心线程执行
        threadPoolExecutor.execute(new MyRunnable("2"));
        //塞入队列
        threadPoolExecutor.execute(new MyRunnable("3"));
        //塞入队列
        threadPoolExecutor.execute(new MyRunnable("4"));
        //塞入队列
        threadPoolExecutor.execute(new MyRunnable("5"));
        //队列塞满了创建多余线程去执行
        threadPoolExecutor.execute(new MyRunnable("6"));
        //队列塞满了创建多余线程去执行
        threadPoolExecutor.execute(new MyRunnable("7"));
        //已达到最大线程数，再执行会报错（如果没报错是因为有线程已经执行完毕了，所以可以执行此任务）
        //threadPoolExecutor.execute(new MyRunnable("8"));
        //上代码会创建两个核心线程和两个多余线程，休息1s，此时所有的任务都执行完毕了，
        //但是多余线程还存活着，此时再执行任务，任务会先放到队列中，然后所有线程从队列中取任务去执行
        //如果塞得任务数超过了队列的容量，就会报错
        //Thread.sleep(1000);
        //如果是休息3s，那么多余线程已经死亡，此时会先塞队列，队列塞满了还能创建多余线程去执行任务
        //如果设置allowCoreThreadTimeOut(true)，那核心线程也死亡了，此时再执行任务就和新的线程池流程一样了
        Thread.sleep(3000);
        threadPoolExecutor.execute(new MyRunnable("9"));
        threadPoolExecutor.execute(new MyRunnable("10"));
        threadPoolExecutor.execute(new MyRunnable("11"));
        threadPoolExecutor.execute(new MyRunnable("12"));
        threadPoolExecutor.execute(new MyRunnable("13"));


        Thread.sleep(5000);
    }
}

class MyRunnable implements Runnable {
    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + "----" + name);
    }
}