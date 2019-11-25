package com.yang.core.thread.pool;

import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @ClassName: TaskExecutorTest
 * @Description: 线程池测试
 * @author: tonasun
 * @date: 2017年3月7日 下午4:39:00
 */

public class TaskExecutorTest {

	@Test
	public void taskExecutorTest() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setCorePoolSize(5);
		taskExecutor.setQueueCapacity(20);
		taskExecutor.initialize();
		taskExecutor.execute(new MyThread());
	}
}
