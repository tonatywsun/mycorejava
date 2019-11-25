package com.yang.core.thread.sync;

public class MySyncThread1 extends Thread {
    private MySyncClass mySyncClass;

    public MySyncThread1(MySyncClass mySyncClass) {
        super();
        this.mySyncClass = mySyncClass;
    }

    public void run() {
        mySyncClass.service1();
    }
}
