package com.yang.core.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Data {
	private int data;// 共享数据
	ReadWriteLock lock = new ReentrantReadWriteLock();

	public void set(int data) {
		Lock writeLock = lock.writeLock();
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
		Lock readLock = lock.readLock();
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
