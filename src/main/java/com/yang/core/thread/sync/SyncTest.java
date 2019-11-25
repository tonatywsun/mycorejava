package com.yang.core.thread.sync;

public class SyncTest {
    public static void main(String[] args) {
        MySyncClass mySyncClass = new MySyncClass();
        new MySyncThread1(mySyncClass).start();
        // 要等一段时间才执行，说明两个方法是一个对象（mySyncClass）锁
        new MySyncThread2(mySyncClass).start();
    }
}
