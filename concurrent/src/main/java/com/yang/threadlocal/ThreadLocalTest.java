package com.yang.threadlocal;

import java.text.SimpleDateFormat;

/**
 * @Description: SimpleDateFormat的tosting打印的后面数字是hashcode，SimpleDateFormat的hashCode是pattern的hashcode,
 * 如果两个对象pattern一样则hashcode一样则tostring一样但是两个对象不是同一个对象
 * <p>
 * 每个thread都有一个ThreadLocal.ThreadLocalMap，ThreadLocalMap是默认修饰符修饰的，只有本包可以访问，外面无法访问，map中有一个private Entry[] table，
 * 第key.threadLocalHashCode & (len-1)位置存放着key和value,key是当前ThreadLocal
 * @Author: tona.sun
 * @Date: 2019/11/21 11:37
 */
public class ThreadLocalTest {
    static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            //tl.get()中获取的是当前线程的ThreadLocal.ThreadLocalMap threadLocals map中有一个private Entry[] table
            for (int i = 0; i < 10; i++) {
                System.gc();
                if (tl.get() == null) {
                    System.out.println("set");
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1---" + tl.get());
            }

        });
        t1.start();
        Thread.sleep(10000);
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (tl.get() == null) {
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                System.out.println("2---" + tl.get());
            }
        });
        t2.start();
    }

}
