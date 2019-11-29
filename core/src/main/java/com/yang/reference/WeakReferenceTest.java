package com.yang.reference;

import java.lang.ref.WeakReference;

//弱引用
public class WeakReferenceTest {
    public static void main(String[] args) {
        // 对象数量为1000000,保证使得堆内存溢出
        int count = 1000000;
        // 使用弱引用后
        @SuppressWarnings("rawtypes")
        WeakReference[] values = new WeakReference[count];
        for (int i = 0; i < count; i++) {
            // 使用弱引用后,原本由于堆内存溢出而无法正常执行的代码段“正常的”执行成功
            values[i] = new WeakReference<BiggerObject>(new BiggerObject("Object-" + i));
        }
        // 当堆内存足够时，强制进行垃圾回收，对象会被回收
        System.gc();
        for (int i = count - 1; i > 0; i--) {
            // 一个对象都没有
            System.out.println(((BiggerObject) (values[i].get())).name);
        }
    }
}
