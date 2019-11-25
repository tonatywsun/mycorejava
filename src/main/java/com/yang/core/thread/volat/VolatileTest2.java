package com.yang.core.thread.volat;


/**
 * @Description: volatile一个作用测试
 * @Author: tona.sun
 * @Date: 2019/11/14 10:17
 */

class VolatileTestThread extends Thread {
    /**
     * @description : 如果不加volatile修饰，主线程虽然讲此值修改为false但是A线程不会从主内存中去读取所以线程不会执行完毕
     */
    public /*volatile*/ static Boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        System.out.println("thread start...");
        // 此处使用的是引用理论上其他线程的修改对其是可见的，但是如果while块中不写代码这里就不可见，应该是加载器做了优化成了while(true){}
        while (flag.booleanValue()) {
            //System.out.println(1);
            /* System.out.println(flag.booleanValue());*/
        }
        System.out.println(flag.booleanValue());
        System.out.println("thread end...");
    }

    /*public void setRuning(boolean flag) {
        this.flag = flag;
    }*/
}

public class VolatileTest2 {
    public static void main(String[] args) throws InterruptedException {
        VolatileTestThread threadDemo = new VolatileTestThread();
        threadDemo.start();
        Thread.sleep(2000);
        VolatileTestThread.flag = new Boolean(false);
        /*threadDemo004.setRuning(false);*/
        System.out.println("flag set false!");
        Thread.sleep(1000);
        System.out.println("flag:" + VolatileTestThread.flag);
    }
}
