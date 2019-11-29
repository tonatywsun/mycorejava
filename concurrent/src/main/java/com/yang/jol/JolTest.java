package com.yang.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Description: 对象在堆中由对象头、实例数据、对齐数据三部分组成。
 * 其中对象头大小固定12个字节(64位jvm).
 * Entry中有一个char实例，占2个字节(注意是char，不是对象没有对象头，所以才会小于头大小固定12个字节)（如果没有实例就没有此项）
 * 64位jvm规定对象所占内存必须为8的整数倍位数，所以对齐数据为2为，拼在一起Entry占16个字节（如果头+实例为8的整数倍就没有此项）
 * Mark Word 64bit每个bit的意思参照mark_word.png,只有调用了hashcode只有Mark_word中hashcode位置才会有值，Mark Word中有锁的状态（windows系统是小端存储，倒着看）
 * 所以synchronized关键字实现原理就是通过修改Mark Word中关于锁状态的值
 *
 *    jvm          规范
 * HotSpot VM    虚拟机产品  (sun公司亲儿子)、
 * 此外还有其他各种虚拟机 如taobaojvm
 *
 * openjdk 项目，代码   HotSpot中大部分是通过openjdk实现的
 *
 * Mark Word  64bit
 * class pointer
 * array length
 * @Author: tona.sun
 * @Date: 2019/11/29 10:36
 */
public class JolTest {
    public static void main(String[] args) {
        Entry entry = new Entry();
        synchronized (entry){
            System.out.println(Integer.toHexString(entry.hashCode()));
            System.out.println(ClassLayout.parseInstance(entry).toPrintable());
        }
    }
}
