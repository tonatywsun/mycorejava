package com.yang.other.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ThreadTest2 extends Thread {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private boolean sleep;
    private String name;
    private String dateStr;

    public ThreadTest2(String name, String dateStr, boolean sleep) {
        this.sleep = sleep;
        this.name = name;
        this.dateStr = dateStr;
    }

    @Override
    public void run() {
        Date date = null;
        // 判断是否需要休息一下
        if (sleep) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            //System.out.println(dateStr);
            date = DateFormatUtil.parse(dateStr, "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(name + " : date: " + DateFormatUtil.format(date, "yyyy-MM-dd"));
    }
}
