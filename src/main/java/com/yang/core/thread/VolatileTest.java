package com.yang.core.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**  
* @Description: TODO
* @ClassName: VolatileTest   
* @author tonasun  
* @date 2018年6月7日  
*    https://www.cnblogs.com/dolphin0520/p/3920373.html
*/  
public class VolatileTest {
    // Lock lock = new ReentrantLock();
    public AtomicInteger inc = new AtomicInteger();

    public /* synchronized */void increase() {

        /*
         * lock.lock(); try { inc++; } catch (Exception e) { } finally { lock.unlock();
         * }
         */
        inc.getAndIncrement();
    }

    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for (int i = 0; i < 100; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 10000; j++)
                        test.increase();
                };
            }.start();
        }

        while (Thread.activeCount() > 1) // 保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }

    @Test
    public void test3() {
        new Thread() {
            public void run() {
                test1();
            };
        }.start();
        new Thread() {
            public void run() {
                test2();
            };
        }.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    String context = null;
    boolean inited = false;

    public void test1() {
        // 线程1:
        context = loadContext();
        inited = true;
    }

    public String loadContext() {
        String a = "";
        for (int i = 1; i < 10000; i++) {
            a = a + i;
        }
        return "context";
    }

    public void test2() {
        // 线程2:
        while (!inited) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        doSomethingwithconfig(context);
    }

    public void doSomethingwithconfig(String context) {
        if (context == null) {
            System.out.println(context);
        }
    }
}
