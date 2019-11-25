package com.yang.core.thread.sync;

public class MySyncThread2 extends Thread {
    private MySyncClass mySyncClass;

    public MySyncThread2(MySyncClass mySyncClass) {
        super();
        this.mySyncClass = mySyncClass;
    }

    public void run() {
        mySyncClass.service2();
    }
}
