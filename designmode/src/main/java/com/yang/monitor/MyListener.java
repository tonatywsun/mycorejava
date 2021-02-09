package com.yang.monitor;

/**
 * 被监听
 *
 * @author tona.sun
 * @version V1.0
 * @className: MyListener
 * @date 2021/1/11 12:00
 */
public class MyListener implements Listener{
    @Override
    public void action() {
        System.out.println("MyListener");
    }
}
