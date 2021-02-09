package com.yang.flowapi;

import java.util.concurrent.Flow;

/**
 * TODO(文件概要)
 *
 * <p> TODO(具体描述信息,可以不填)
 *
 * @author tona.sun
 * @version V1.0
 * @className: CountPublisher
 * @date 2020/12/24 17:53
 */
public class CountPublisher implements Flow.Publisher<Integer> {
    /**
     * 需要的计数值
     */
    private int count = 0;
    /**
     * 发送间隔
     */
    private final long interval;
    /**
     * 发送间隔
     */
    private boolean isCanceled;

    public CountPublisher(long interval) {
        this.interval = interval;
        isCanceled = false;
    }

    @Override
    public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
        new Thread(() -> {
            try {
                subscriber.onSubscribe(new CountSubscription());
                for (int i = 0; i < count && !isCanceled; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).start();
    }

    private class CountSubscription implements Flow.Subscription {
        @Override
        public void request(long n) {
            count += n;//根据请求数新增计数值
        }

        @Override
        public void cancel() {
            isCanceled = true;//将标志位置为已取消
        }
    }
}
