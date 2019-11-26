package com.yang.core.thread.callable;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : tona.sun
 * @description : Futrure模式:对于多线程，如果线程A要等待线程B的结果，那么线程A没必要等待B，直到B有结果，可以先拿到一个未来的Future，等B有结果是再取真实的结果。
 * 　在多线程中经常举的一个例子就是：网络图片的下载，刚开始是通过模糊的图片来代替最后的图片，等下载图片的线程下载完图片后在替换。而在这个过程中可以做一些其他的事情。
 * @date : 2019/11/25 17:18
 */
public class CallableTask {
    @Test
    public void callableTaskTest() {
        MyCallable task = new MyCallable();
        ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
        //Future模式的核心在于去除了主函数的等待时间，并使得原本需要等待的时间段可以用于处理其他业务逻辑
        Future<String> result = executor.submit(task);
        //中间这段时间可以做其他事情
        System.out.println("主线程在执行任务");
        //试图取消任务 true已经执行中也试图去取消  false已经执行中的不取消 返回取消是否成功
        //boolean cancel = result.cancel(true);
        //如果任务完成前被取消，则返回true
        //boolean cancelled = result.isCancelled();
        //判断任务是否执行完毕
        if (result.isDone()) {
            try {
                // 这个方法是阻塞的，得不到会等待
                System.out.println("task运行结果" + result.get());
                // 只会执行一次,再次调用直接取值
                System.out.println("task运行结果" + result.get());
                System.out.println("task运行结果" + result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("所有任务执行完毕");
        executor.shutdown();
    }
}
