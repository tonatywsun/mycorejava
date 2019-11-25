package com.yang.core.thread.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class CallableTask {
	@Test
	public void callableTaskTest() {
		// 线程池
		MyCallable task = new MyCallable();
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<String> result = executor.submit(task);
		System.out.println("主线程在执行任务");
		try {
			// 得不到会等待
			System.out.println("task运行结果" + result.get());
			// 只会执行一次,再次调用直接取值
			System.out.println("task运行结果" + result.get());
			System.out.println("task运行结果" + result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("所有任务执行完毕");
		executor.shutdown();
	}
}
