package com.yang.other.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ThreadTest extends Thread {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private boolean sleep;
    private String name;
    private String dateStr;

    public ThreadTest(String name, String dateStr, boolean sleep) {
        this.sleep = sleep;
        this.name = name;
        this.dateStr = dateStr;
    }

    @Override
    public void run() {
        Date date = null;
        if (sleep) {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println(dateStr);
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(name + " : date: " + date);
    }
}
