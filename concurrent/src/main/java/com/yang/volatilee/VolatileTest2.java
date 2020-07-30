package com.yang.volatilee;


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
        //而加了输出或者sleep语句之后，CPU就有可能有时间去保证内存的可见性，于是while循环可以被终止。
        while (flag.booleanValue()) {
            //把while循环代码里加上任意一个输出语句或者sleep方法你会发现死循环也会停止，不管isRunning变量是否被加上了上volatile关键字
            /* 因为：JVM会尽力保证内存的可见性，即便这个变量没有加同步关键字。换句话说，只要CPU有时间，JVM会尽力去保证变量值的更新。
             * 这种与volatile关键字的不同在于，volatile关键字会强制的保证线程的可见性。
             * 而不加这个关键字，JVM也会尽力去保证可见性，但是如果CPU一直有其他的事情在处理，它也没办法。
             * 最开始的代码，一直处于死循环中，CPU处于一直占用的状态，这个时候CPU没有时间，JVM也不能强制要求CPU分点时间去取最新的变量值。
             * 而加了输出或者sleep语句之后，CPU就有可能有时间去保证内存的可见性，于是while循环可以被终止。
             * */
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
