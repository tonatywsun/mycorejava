package com.yang.monitor;

/**
 * TODO(文件概要)
 *
 * <p> TODO(具体描述信息,可以不填)
 *
 * @author tona.sun
 * @version V1.0
 * @className: Test
 * @date 2021/1/11 13:57
 */
public class Test {
    public static void main(String[] args) {
        MyEvent event= new MyEvent();
        event.addListener(new MyListener());
        event.multicast();
    }
}
