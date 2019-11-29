package com.yang.disruptor.factory;


import com.lmax.disruptor.EventFactory;
import com.yang.disruptor.entity.LongEvent;

// EventFactory 实例化LongEvent
public class LongEventFactory implements EventFactory<LongEvent> {

	@Override
	public LongEvent newInstance() {

		return new LongEvent();
	}

}
