package com.yang.synchronizedd;

/**
 * @author : tona.sun
 * @description : Synchronized测试类，懒得写了
 * 1.修饰需要进行同步的方法，此时充当锁的对象为调用同步方法的对象
 * 2.同步代码块和直接使用synchronized修饰需要同步的方法是一样的，但是锁的粒度可以更细，并且充当锁的对象不一定是this，也可以是其它对象，所以使用起来更加灵活
 * 3.修饰需要进行同步的静态方法，此时充当锁的对象为当前类
 * @date : 2019/11/27 17:21
 */
public class SynchronizedTest {

}
