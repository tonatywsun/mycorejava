package com.yang.core.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 多线程之栅栏
 *
 * @author tonasun
 */
public class CellularTest {
    private CyclicBarrier cyclicBarrier;

    public CellularTest() {
    }

    public CyclicBarrier getCyclicBarrier(final int count) {
        // 创建一个新的 CyclicBarrier，它将在给定数量的参与者（线程）处于等待状态时启动，并在启动 barrier
        // 时执行给定的屏障操作，该操作由最后一个进入 barrier 的线程执行。
        cyclicBarrier = new CyclicBarrier(count, () -> {
            System.out.println("有" + count + "个人到了，带他们上车");
        });
        return cyclicBarrier;
    }

    public static void main(String[] args) {
        int count = 3;
        final CellularTest cellularTest = new CellularTest();
        final CyclicBarrier cyclicBarrier = cellularTest.getCyclicBarrier(count);
        for (int i = 0; i < 9; ++i) {
            final int x = i;
            Thread thread = new Thread() {
                @Override
                public synchronized void run() {
                    try {
                        this.setName(x + "号乘客");
                        System.out.println(this.getName() + "到了，等待乘务员发话");
                        // 某个线程执行了此方法则次线程被此栅栏拦截，等到了count个线程之后再放行,会执行回调方法
                        cyclicBarrier.await();
                        System.out.println(this.getName() + "上车了！");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }
}
