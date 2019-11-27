package com.yang.queue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @Author: tona.sun
 * @Date: 2019/11/22 14:07
 */
public class BlockingQueueTest {
    @Test
    public void test() {
        //定长 阻塞队列
        BlockingQueue<String> abq = new ArrayBlockingQueue<>(3);
        abq.add("1");
        abq.offer("2");
        try {
            // 不会等待，因为没满
            abq.offer("3", 3, TimeUnit.SECONDS);
            // 如果队列满了 会等待3秒之后放不成功则返回false
            System.out.println(abq.offer("4", 3, TimeUnit.SECONDS));
        } catch (Exception e) {
        }
        //如果满了还add会抛出异常
        //abq.add("5");
        //如果慢了不会等待直接返回false
        System.out.println(abq.offer("6"));
    }

    @Test
    public void test2() {
        //定长(不指定长度则长度为INTEGER.MXA)  阻塞队列
        BlockingQueue<String> abq = new LinkedBlockingQueue<>(3);
        abq.add("1");
        abq.offer("2");
        try {
            // 不会等待，因为没满
            abq.offer("3", 3, TimeUnit.SECONDS);
            // 如果队列满了 会等待3秒之后放不成功则返回false
            System.out.println(abq.offer("4", 3, TimeUnit.SECONDS));
        } catch (Exception e) {
        }
        //如果满了还add会抛出异常
        //abq.add("5");
        //如果慢了不会等待直接返回false
        System.out.println(abq.offer("6"));
    }
}
