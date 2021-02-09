package com.yang.monitor;

import java.util.LinkedList;
import java.util.List;

/**
 * 被监听
 *
 * @author tona.sun
 * @version V1.0
 * @className: MyEvent
 * @date 2021/1/11 10:56
 */
public class MyEvent {
    List<Listener> listeners = new LinkedList<>();

    public Boolean addListener(Listener listener) {
        return listeners.add(listener);
    }

    public void multicast() {
        listeners.forEach(Listener::action);
    }


}
