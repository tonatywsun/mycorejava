package com.yang.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Description: 对象在堆中由对象头、实例数据、对齐数据三部分组成。
 * 其中对象头大小固定12个字节(64位jvm).
 * Entry中有一个char实例，占2个字节(注意是char，不是对象没有对象头，所以才会小于头大小固定12个字节)（如果没有实例就没有此项）
 * 64位jvm规定对象所占内存必须为8的整数倍位数，所以对齐数据为2位，拼在一起Entry占16个字节（如果头+实例为8的整数倍就没有此项）
 * Mark Word 64bit每个bit的意思参照mark_word.png,只有调用了hashcode只有Mark_word中hashcode位置才会有值，Mark Word中有锁的状态（windows系统是小端存储，倒着看）
 * 所以synchronized关键字实现原理就是通过修改Mark Word中关于锁状态的值
 * <p>
 * jvm          规范
 * HotSpot VM    虚拟机产品  (sun公司亲儿子)、
 * 此外还有其他各种虚拟机 如taobaojvm
 * <p>
 * openjdk 项目，代码   HotSpot中大部分是通过openjdk实现的
 * <p>
 * Mark Word  64bit
 * class pointer
 * array length
 * @Author: tona.sun
 * @Date: 2019/11/29 10:36
 */
public class JolTest {
    public static void main(String[] args) {
        Entry entry = new Entry();
        new Thread(() -> {
            while (true) {
                synchronized (entry) {
                    // System.out.println(Integer.toHexString(entry.hashCode()));
                    System.out.println(ClassLayout.parseInstance(entry).toPrintable());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //锁主要存在四中状态，依次是：无锁状态、偏向锁状态、轻量级锁状态、重量级锁状态。他们会随着竞争的激烈而逐渐升级。注意锁可以升级不可降级，这种策略是为了提高获得锁和释放锁的效率。
        //当多个线程去争夺锁的时候，锁的状态会发生改变，偏向锁->轻量级锁->重量级锁，观察对象头可以观察到对象头回发生改变
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (entry) {
                        System.out.println("2222222222");
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
