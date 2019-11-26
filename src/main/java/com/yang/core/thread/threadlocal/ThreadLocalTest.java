package com.yang.core.thread.threadlocal;

import java.text.SimpleDateFormat;

/**
 * @Description: SimpleDateFormat的tosting打印的后面数字是hashcode，SimpleDateFormat的hashCode是pattern的hashcode,
 * 如果两个对象pattern一样则hashcode一样则tostring一样但是两个对象不是同一个对象
 * <p>
 * 每个thread都有一个ThreadLocal.ThreadLocalMap，ThreadLocalMap是默认修饰符修饰的，只有本包可以访问，外面无法访问，map中有一个private Entry[] table，
 * 第key.threadLocalHashCode & (len-1)位置存放着key和value
 * @Author: tona.sun
 * @Date: 2019/11/21 11:37
 */
public class ThreadLocalTest {
    static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal();

    public static void main(String[] args) {
        int i = 0;
        i=i++;
        System.out.println(i);
        Thread t1 = new Thread(() -> {
            if (tl.get() == null) {
                tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            }
            System.out.println("1---" + tl.get());
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            if (tl.get() == null) {
                tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            }
            System.out.println("2---" + tl.get());
        });
        t2.start();
    }

}
