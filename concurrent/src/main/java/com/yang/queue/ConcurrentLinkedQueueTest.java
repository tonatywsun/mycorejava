package com.yang.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Description: ConcurrentLinkedQueue测试
 * @Author: tona.sun
 * @Date: 2019/11/22 11:09
 */
public class ConcurrentLinkedQueueTest {
    public static void main(String[] args) {
        new ConcurrentLinkedQueueTest().test();
    }

    public void test() {
        // 无界队列 非阻塞
        ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<>();
        boolean add = clq.add("1");
        boolean offer = clq.offer("2");
        int size = clq.size();
        System.out.println(size);
        //取一个元素 会删除
        /*String poll = clq.poll();
        System.out.println(poll);
        String poll1 = clq.poll();
        System.out.println(poll1);*/
        //取一个但不删除所以下次这样去还会取到这个元素
        String peek = clq.peek();
        System.out.println(peek);
        String peek1 = clq.peek();
        System.out.println(peek1);
        int size1 = clq.size();
        System.out.println(size1);
    }
}
