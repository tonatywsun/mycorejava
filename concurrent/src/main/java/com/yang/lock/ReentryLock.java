package com.yang.lock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 重入锁，也叫做递归锁，指的是 同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响，避免死锁
 * @Author: tona.sun
 * @Date: 2019/11/26 11:30
 */
public class ReentryLock {
    @Test
    public void test() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                sync();
            }).start();
        }
    }

    public void sync() {
        //当线程在此处获取到锁后可以传入到sync2方法中，sync2中不需要在获取锁，避免死锁，具有可重入性
        synchronized (ReentryLock.class) {
            sync2();
        }
    }

    public void sync2() {
        synchronized (ReentryLock.class) {
            System.out.println(Thread.currentThread());
            synchronized (ReentryLock.class) {
                System.out.println(Thread.currentThread());
            }
        }
    }


}

/**
 * @author : tona.sun
 * @description : 根据ReentrantLock自己写的aqs的理解，这只是ReentrantLock对aqs的使用，当然aqs还有其他使用方式用于实现其他类型的锁
 * @date : 2019/11/28 17:12
 */
@SuppressWarnings("all")
class ReentrantLockThread extends Thread {
    ReentrantLock lock = new ReentrantLock(true);

    public void get() {
        //当线程在此处获取到锁后可以传入到set方法中，set中不需要在获取锁，避免死锁，具有可重入性

        /**
         * 通过lock看源码 以FairSync（公平锁）进行研究
         *ReentrantLock中静态类abstract static class Sync extends AbstractQueuedSynchronizer衍生出两个子类NonfairSync（非公平锁）和FairSync（公平锁）
         * public void lock() {
         *     //加一层锁
         *     sync.acquire(1);
         * }
         * //获取锁方法
         * public final void acquire(int arg) {
         *      //尝试获取锁 当tryAcquire(arg)返回true获取到锁，!tryAcquire(arg)为false直接获取到锁，结束。
         *      //如果获取锁失败，就执行acquireQueued(addWaiter(Node.EXCLUSIVE), arg)
         *     if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg)){
         *         selfInterrupt();
         *     }
         * }
         *
         * //尝试获取锁
         * protected final boolean tryAcquire(int acquires) {
         *     //获取当前线程
         *     final Thread current = Thread.currentThread();
         *     //获取AbstractQueuedSynchronizer类中的int state,第一个线程来的时候为默认值0（未加锁）
         *      //第二个线程进来时且第一个线程未释放锁，那么c!=0
         *     int c = getState();
         *     if (c == 0) {
         *          //第一个线程进来c==0,hasQueuedPredecessors先判断链表中是否有前一个元素，第一次肯定是没有的，返回false，!hasQueuedPredecessors()为true
         *          //compareAndSetState(0, acquires)比较state是否是0，如果是的就把state设为acquires(1)
         *          //公平和非公平的区别就在!hasQueuedPredecessors() 非公平不会判断链表中的元素，只要上锁的层数为0拿能到锁，可能会插队
         *         if (!hasQueuedPredecessors() &&compareAndSetState(0, acquires)) {
         *              //设置锁拥有者为当前线程
         *             setExclusiveOwnerThread(current);
         *             return true;
         *         }
         *     }
         *     //如果线程是锁的拥有者就是重入
         *     else if (current == getExclusiveOwnerThread()) {
         *          //把锁的数量加上acquires层就可以了
         *         int nextc = c + acquires;
         *         if (nextc < 0){
         *             throw new Error("Maximum lock count exceeded");
         *         }
         *         //设置层数
         *         setState(nextc);
         *         return true;
         *     }
         *     return false;
         * }
         *
         *
         * public final boolean hasQueuedPredecessors() {
         *     Node h, s;
         *     if ((h = head) != null) {
         *         if ((s = h.next) == null || s.waitStatus > 0) {
         *             s = null; // traverse in case of concurrent cancellation
         *             for (Node p = tail; p != h && p != null; p = p.prev) {
         *                 if (p.waitStatus <= 0){
         *                     s = p;
         *                 }
         *             }
         *         }
         *         if (s != null && s.thread != Thread.currentThread())
         *             return true;
         *         }
         *     return false;
         * }
         *
         * //new一个node,node的 volatile Thread thread;属性设为当前线程并把这个node放的链表尾部
         * private Node addWaiter(Node mode) {
         *      //Node(Node nextWaiter) {
         *      //    this.nextWaiter = nextWaiter;
         *      //   THREAD.set(this, Thread.currentThread());
         *      //}
         *      //new一个node，node的 volatile Thread thread;属性设为当前线程
         *     Node node = new Node(mode);
         *     for (;;) {
         *        //tail表示AbstractQueuedSynchronizer中的链表末尾，第二个线程进来时为null
         *        //第二次循环的时候tail就不是null了是initializeSyncQueue中new的一个node
         *        Node oldTail = tail;
         *        if (oldTail != null) {
         *            //将oldTail设为node的prev
         *             node.setPrevRelaxed(oldTail);
         *             //设置tail（末尾）为node
         *             if (compareAndSetTail(oldTail, node)) {
         *                  //组成了一个链表node被加到了尾部传入到acquireQueued方法中
         *                 oldTail.next = node;
         *                 return node;
         *             }
         *         } else {
         *              //设置AbstractQueuedSynchronizer中tail为一个new Node()，如何head为null（第二个线程来的时候是为null）head也等于这个node
         *             initializeSyncQueue();
         *         }
         *     }
         * }
         *
         *
         * final boolean acquireQueued(final Node node, int arg) {
         *     boolean interrupted = false;
         *     try {
         *         for (;;) {
         *              //取node的prev
         *             final Node p = node.predecessor();
         *             //判断prev是不是头，如何是则再尝试去获取锁
         *             if (p == head && tryAcquire(arg)) {
         *                 setHead(node);
         *                 p.next = null; // help GC
         *                 return interrupted;
         *             }
         *             //prev不是头就修改prev的状态，下次循环可以通过状态做处理
         *             if (shouldParkAfterFailedAcquire(p, node))
         *                  //线程无法获取到锁就park
         *                 interrupted |= parkAndCheckInterrupt();
         *         }
         *    } catch (Throwable t) {
         *         cancelAcquire(node);
         *         if (interrupted)
         *             selfInterrupt();
         *         throw t;
         *     }
         * }
         * //使用park将线程阻断
         * private final boolean parkAndCheckInterrupt() {
         *     LockSupport.park(this);
         *     return Thread.interrupted();
         * }
         */
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        set();
        /**
         * //解锁
         * public final boolean release(int arg) {
         *      //尝试解锁
         *     if (tryRelease(arg)) {
         *         Node h = head;
         *         //头不是null切状态不是初始，如果在其后面有node会修改其waitStatus不会是0
         *         if (h != null && h.waitStatus != 0)
         *             unparkSuccessor(h);
         *         return true;
         *     }
         *     return false;
         * }
         *
         * protected final boolean tryRelease(int releases) {
         *      //减去层数
         *     int c = getState() - releases;
         *     //如不是线程拥有者直接抛异常
         *     if (Thread.currentThread() != getExclusiveOwnerThread())throw new IllegalMonitorStateException();
         *     boolean free = false;
         *     //是否还有层数（重入有可能没解完）
         *     if (c == 0) {
         *        free = true;
         *        //设置线程拥有者为null
         *        setExclusiveOwnerThread(null);
         *     }
         *     //设置层数
         *     setState(c);
         *     return free;
         * }
         *
         * private void unparkSuccessor(Node node) {
         *    int ws = node.waitStatus;
         *    if (ws < 0)
         *        //将当前node状态设置为初始状态
         *        node.compareAndSetWaitStatus(ws, 0);
         *    //拿到他下面的node
         *    Node s = node.next;
         *    if (s == null || s.waitStatus > 0) {
         *        s = null;
         *        //从后往前取一个p.waitStatus <= 0&&p != node && p != null的node
         *        for (Node p = tail; p != node && p != null; p = p.prev)
         *            if (p.waitStatus <= 0)
         *                s = p;
         *    }
         *    if (s != null)
         *        //唤醒这个node中的thread
         *        LockSupport.unpark(s.thread);
         * }
         */
        lock.unlock();
    }

    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockThread ss = new ReentrantLockThread();
        new Thread(ss).start();
        new Thread(ss).start();
        //new Thread(ss).start();
    }


}