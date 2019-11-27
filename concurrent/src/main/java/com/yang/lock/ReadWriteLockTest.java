package com.yang.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : tona.sun
 * @description : 相比Java中的锁(Locks in Java)里Lock实现，读写锁更复杂一些。
 * 假设你的程序中涉及到对一些共享资源的读和写操作，且写操作没有读操作那么频繁。在没有写操作的时候，两个线程同时读一个资源没有任何问题，所以应该允许多个线程能在同时读取共享资源。
 * 但是如果有一个线程想去写这些共享资源，就不应该再有其它线程对该资源进行读或写（读-读能共存，读-写不能共存，写-写不能共存）。这就需要一个读/写锁来解决这个问题
 * @date : 2019/11/26 11:49
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        final Data data = new Data();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    data.get();
                }
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    data.set(new Random().nextInt(30));
                }
            }).start();
        }
    }
}

class Data {
    // 共享数据
    private volatile int data;
    // 读写锁
    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();
    public void set(int data) {
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "准备写入数据");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data = data;
            System.out.println(Thread.currentThread().getName() + "写入" + this.data);
        } finally {
            writeLock.unlock();
        }
    }

    public void get() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + "准备读取数据");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "读取" + this.data);
        } finally {
            readLock.unlock();
        }
    }
}
