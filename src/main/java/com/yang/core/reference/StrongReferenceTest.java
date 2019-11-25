package com.yang.core.reference;

/**
 * 强引用是指在程序代码中普遍存在的，类似“Object obj=newObject()”这类的引用，只要强引用还存在，垃圾收集器永远不会回收掉被引用的对象。
 * 
 * @date 2018年4月19日 上午11:16:24
 * @author tonasun
 */
public class StrongReferenceTest {
    public static void main(String[] args) {
        // 对象的个数,很多,保证使得堆内存溢出
        int count = 1000000;
        BiggerObject[] values = new BiggerObject[count];
        for (int i = 0; i < count; i++) {
            // 对象过多所以发生了内存溢出java.lang.OutOfMemoryError: Java heap space
            values[i] = new BiggerObject("Object-" + i);
        }
        for (int i = count - 1; i > 0; i--) {
            System.out.println(values[i].name);
        }
    }
}