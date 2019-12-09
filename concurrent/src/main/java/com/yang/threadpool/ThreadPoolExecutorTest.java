package com.yang.threadpool;

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
    private static final int COUNT_BITS = Integer.SIZE - 3;
    //前三位状态位的值
    private static final int RUNNING = -1 << COUNT_BITS;//111
    private static final int SHUTDOWN = 0 << COUNT_BITS;//000
    private static final int STOP = 1 << COUNT_BITS;//001
    private static final int TIDYING = 2 << COUNT_BITS;//010
    private static final int TERMINATED = 3 << COUNT_BITS;//011
    private static final int COUNT_MASK = (1 << COUNT_BITS) - 1;

    public static void main(String[] args) {
        System.out.println("RUNNING" + Integer.toBinaryString(RUNNING));
        System.out.println("SHUTDOWN" + Integer.toBinaryString(SHUTDOWN));
        System.out.println("STOP" + Integer.toBinaryString(STOP));
        System.out.println("TIDYING" + Integer.toBinaryString(TIDYING));
        System.out.println("TERMINATED" + Integer.toBinaryString(TERMINATED));
        System.out.println("COUNT_MASK" + Integer.toBinaryString(COUNT_MASK));
    }

    /*execute源码解析
      public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();
        // ctl初始数值为 （-1 << 29 |0）->11100000000000000000000000000000，其中前三位表示线程池状态，后29位数表示线程池中线程数量
        int c = ctl.get();
        //workerCountOf(c),(-1 << 29 | 0) & ((1 << 29) - 1)取后29程池中线程数量
        if (workerCountOf(c) < corePoolSize) {
            if (addWorker(command, true))
                return;
            c = ctl.get();
        }
        //c<0,只有RUNNING状态前三位是111为负值才会小于0，然后放到队列
        if (isRunning(c) && workQueue.offer(command)) {
            int recheck = ctl.get();
            //再次检验
            if (! isRunning(recheck) && remove(command))
                reject(command);
                //如果运行线程数为0，新增一个工作线程
            else if (workerCountOf(recheck) == 0)
                addWorker(null, false);
        }
        //最后当队列满了。直接新增工作线程，失败就执行拒绝策略
        else if (!addWorker(command, false))
            reject(command);
        }
    */


    @Test
    public void test() throws Exception {
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(3);
        /*
         *corePoolSize:线程池中的核心线程数，空闲的线程也不会回收，除非把allowCoreThreadTimeOut设置为true，这时核心线程超过存活时间才会被回收
         *maximumPoolSize:线程池中可以同时运行的最大线程数,当使用无界的阻塞队列的时候队列是塞不满的，所以不会创建核心线程数之外的线程去运行，此值就是无效的
         *keepAliveTime:当线程池中创建的线程超过了核心线程数的时候，这些多余的空闲线程在结束之前等待新的任务最大的存活时间
         *unit:keepAliveTime的时间单位，可以是纳秒，微秒，毫秒，秒，分钟，小时，天
         *workQueue:存放任务的队列，只有当线程数>核心线程数，才会把其他的任务放入queue,当queue放满之后再往其中塞任务会新建线程去执行这些无法塞入的任务，
         *          新建线程数加核心线程数达到最大线程数时,此时队列满了无法塞入，线程数已达最大无法创建，最后执行拒绝策略（具体的实现）
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

        //上面的代码会创建两个核心线程和两个多余线程，此处sleep 1s，此时所有的任务都执行完毕了，
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

        //关闭线程池，不再接受新任务，但要等队列里面的执行完毕之后才会真正关闭
        threadPoolExecutor.isShutdown();
        //关闭线程池，不再接受新任务，队列里面任务不再执行，但是线程正在执行的任务要执行完毕之后才会真正关闭
        threadPoolExecutor.shutdownNow();
        //是否正在终止
        threadPoolExecutor.isTerminating();
        //线程池是否终止
        while (threadPoolExecutor.isTerminated()) {
        }
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