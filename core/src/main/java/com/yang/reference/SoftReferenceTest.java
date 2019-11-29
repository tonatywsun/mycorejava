package com.yang.reference;

import java.lang.ref.SoftReference;

//软引用
public class SoftReferenceTest {
    public static void main(String[] args) {
        // 对象数量为1000000,很多，保证使得堆内存溢出
        int count = 1000000;
        // 使用软引用后
        @SuppressWarnings("rawtypes")
        SoftReference[] values = new SoftReference[count];
        for (int i = 0; i < count; i++) {
            // 使用软引用后,原本由于堆内存溢出而无法正常执行的代码段“正常的”执行成功
            values[i] = new SoftReference<BiggerObject>(new BiggerObject("Object-" + i));
        }
        // 当堆内存足够时，即使我们强制进行垃圾回收，软引用关联着的对象也不会被回收
        System.gc();
        // 但是，当我们访问早期创建的那些对象时，却报java.lang.NullPointerException异常，说明早期创建的对象已经被垃圾收集器回收了。
        // 可能values[814790]就是null了 因为之前的被回收了
        for (int i = count - 1; i > 0; i--) {
            // 只有一部分对象能打印，为null是抛出NPE
            System.out.println(((BiggerObject) (values[i].get())).name);
        }
    }
}
