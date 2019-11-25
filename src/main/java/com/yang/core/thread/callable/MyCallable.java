package com.yang.core.thread.callable;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
	@Override
	public String call() {
		try {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread() + "sleep 1000");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("子线程在进行计算");
		int sum = 0;
		for (int i = 0; i < 100; i++) {
			sum += i;
		}
		return sum + "";
	}
}