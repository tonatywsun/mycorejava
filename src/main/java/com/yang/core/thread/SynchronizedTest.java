package com.yang.core.thread;

public class SynchronizedTest implements Runnable {
	int b = 100;

	synchronized void m1() throws InterruptedException {
		System.out.println(333);
		b = 1000;
		// 使当前线程（即调用该方法的线程）暂停执行一段时间，让其他线程有机会继续执行，但它并不释放对象锁。
		Thread.sleep(500);
		// 6
		System.out.println("b=" + b);
		System.out.println(444);
	}

	/**
	 * synchronized 直接作用于实例方法:相当于对当前实例加锁，进入同步代码前要获得当前实例的锁。
	 * 直接作用于静态方法:相当于对当前类加锁，进入同步代码前要获得当前类的锁。
	 */
	synchronized void m2() throws InterruptedException {
		System.out.println(111);
		Thread.sleep(250);
		// 5
		b = 2000;
		System.out.println(222);
	}

	public static void main(String[] args) throws InterruptedException {
		SynchronizedTest tt = new SynchronizedTest();
		Thread t = new Thread(tt);
		// 1
		t.start();
		// 2
		tt.m2();
		// 3
		System.out.println("main thread b=" + tt.b);
	}

	@Override
	public void run() {
		try {
			m1();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
