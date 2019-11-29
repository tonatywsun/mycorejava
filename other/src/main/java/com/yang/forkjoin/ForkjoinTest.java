package com.yang.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ForkjoinTest {
	public static void main(String[] args) {
		ProductListGenerator generator = new ProductListGenerator();
		List<Product> products = generator.generate(10000);
		// 创建一个新的Task对象，用来更新产品队列中的产品。first参数使用值0，last参数使用值10000（产品数列的大小）。
		Task task = new Task(products, 0, products.size(), 0.20);
		// 使用无参构造器创建ForkJoinPool对象。
		ForkJoinPool pool = new ForkJoinPool();
		// 在池中使用execute()方法执行这个任务 。
		pool.execute(task);
		do {
			// 此方法返回当前执行任务的线程的数量。
			System.out.println("Main: Thread Count:" + pool.getActiveThreadCount());
			// 此方法返回 long 值，worker 线程已经从另一个线程偷取到的时间数。
			System.out.println("Main: Thread Steal:" + pool.getStealCount());
			// 此方法返回池的并行的级别。
			System.out.println("Main: Parallelism:" + pool.getParallelism());
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
		// 使用shutdown()方法关闭这个池
		pool.shutdown();
		if (task.isCompletedNormally()) {
			System.out.println("Main: The process has completednormally.");
		}
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			if (product.getPrice() != 12) {
				System.out.println("Product " + product.getName() + " " + product.getPrice());
			}
		}
		System.out.println("Main: End of the program.");
	}
}