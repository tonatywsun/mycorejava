package com.yang.flowapi;

/**
 * 为什么要响应式编程？
 * 更简洁的代码，使其更具可读性。
 * 从模板代码中抽离出来，专注于业务逻辑。
 * 从底层的线程，同步和并发问题中抽离出来。
 * 流处理意味着高效的内存
 * 该模型几乎可以应用于任何类型的问题。
 *
 * @author tona.sun
 * @version V1.0
 * @className: FlowTest
 * @date 2020/12/24 17:55
 */
public class FlowTest {
    public static void main(String[] args) throws InterruptedException {
        CountPublisher publisher = new CountPublisher(500L);
        CountSubscriber subscriber = new CountSubscriber(15);
        publisher.subscribe(subscriber);
        Thread.currentThread().join();
    }
}

