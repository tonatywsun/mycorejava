package com.yang.other.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class MyTest {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();

        // A 会sleep 2s 后开始执行sdf.parse()
        executor.execute(new ThreadTest("A", "1991-09-13", false));
        // Thread.sleep(500);
        // B 打了断点,会卡在方法中间
        executor.execute(new ThreadTest("B", "2013-09-13", false));

        executor.shutdown();
    }

    @Test
    public void test1() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            ThreadTest2 threadTest2 = new ThreadTest2(i + "", "199" + i + "-09-13", false);
            executor.execute(threadTest2);
            // 要等等这个线程 不然主线程跑完了它还没跑完
            //threadTest2.join();//不能等，主线程要等这一个走完才走下一个循环，和单线程没区别了
        }
        //System.out.println("123");
        Thread.sleep(200);
        executor.shutdown();
    }
}
