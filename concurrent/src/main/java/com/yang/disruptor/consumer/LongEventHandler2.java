package com.yang.disruptor.consumer;


import com.lmax.disruptor.EventHandler;
import com.yang.disruptor.entity.LongEvent;

//消费者获取生产推送数据
public class LongEventHandler2 implements EventHandler<LongEvent> {

	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch)  {
		System.out.println("消费者2 获取生产者数据..event:" + event.getValue());
	}

}
