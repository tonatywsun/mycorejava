package com.yang.lock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 重入锁，也叫做递归锁，指的是 同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响，避免死锁
 * @Author: tona.sun
 * @Date: 2019/11/26 11:30
 */
public class ReentryLock {
    @Test
    public void test() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                sync();
            }).start();
        }
    }

    public void sync() {
        //当线程在此处获取到锁后可以传入到sync2方法中，sync2中不需要在获取锁，避免死锁，具有可重入性
        synchronized (ReentryLock.class) {
            sync2();
        }
    }

    public void sync2() {
        synchronized (ReentryLock.class) {
            System.out.println(Thread.currentThread());
            synchronized (ReentryLock.class) {
                System.out.println(Thread.currentThread());
            }
        }
    }


}

class ReentrantLockThread extends Thread {
    ReentrantLock lock = new ReentrantLock();

    public void get() {
        //当线程在此处获取到锁后可以传入到set方法中，set中不需要在获取锁，避免死锁，具有可重入性
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        ReentrantLockThread ss = new ReentrantLockThread();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }

}