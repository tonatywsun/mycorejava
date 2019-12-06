package com.yang.volatilee;

/**
 * volatile关键字可以用来提醒编译器它后面所定义的变量随时有可能改变，因此编译后的程序每次需要存储或读取这个变量的时候，都会直接从变量地址中读取数。如果没有volatile关键字，则编译器可能优化读取和存储，可能暂时使用寄存器中的值，如果这个变量由别的程序更新了的话，将出现不一致的现象。
 * 
 * @date 2018年3月26日 下午2:27:45
 * @author tonasun
 */
public class MyThread extends Thread {

	private /* volatile */ boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        long c = 0;
        System.out.println("进入run了");
        // 线程可以把变量保存本地内存（比如机器的寄存器）中，而不是直接在主存中进行读写。这就可能造成一个线程在主存中修改了一个变量的值，而另外一个线程还继续使用它在寄存器中的变量值的拷贝，造成数据的不一致。
        // 引用类型修改的属性先保存在工作内存，然后刷新到堆内存
        // isRunning变量没有加上volatile关键字时，运行以上代码会出现死循环，这是因为isRunning变量虽然被修改但是没有被写到主存中，这也就导致该线程在本地内存中的值一直为true，这样就导致了死循环的产生。
        while (isRunning == true) {
            //volatile关键字能保证数据的可见性，但不能保证数据的原子性。synchronized关键字两者都能保证。
			/*
			 * synchronized (this) { c++; }
			 */
            c++;
            //把while循环代码里加上任意一个输出语句或者sleep方法你会发现死循环也会停止，不管isRunning变量是否被加上了上volatile关键字
            /* 因为：JVM会尽力保证内存的可见性，即便这个变量没有加同步关键字。换句话说，只要CPU有时间，JVM会尽力去保证变量值的更新。
             * 这种与volatile关键字的不同在于，volatile关键字会强制的保证线程的可见性。
             * 而不加这个关键字，JVM也会尽力去保证可见性，但是如果CPU一直有其他的事情在处理，它也没办法。
             * 最开始的代码，一直处于死循环中，CPU处于一直占用的状态，这个时候CPU没有时间，JVM也不能强制要求CPU分点时间去取最新的变量值。
             * 而加了输出或者sleep语句之后，CPU就有可能有时间去保证内存的可见性，于是while循环可以被终止。
             * */
			/* System.out.println(c); */
			/*
			 * try { Thread.sleep(100); } catch (InterruptedException e) {
			 * e.printStackTrace(); }
			 */
        }
        System.out.println("c==" + c);
        System.out.println("线程被停止了！");
    }
}
