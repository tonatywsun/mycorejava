package com.yang.core.string;

import org.junit.Test;

public class InterToString {
    public static void main(String[] args) {
        // int loopTime = (1 << 31) - 1;
        int loopTime = 1000000;
        Integer i = 0;
        long startTime = System.currentTimeMillis();
        // 次之
        for (int j = 0; j < loopTime; j++) {
            String str = String.valueOf(i);
        }
        System.out.println("String.valueOf()：" + (System.currentTimeMillis() - startTime) + "ms");

        // 最快
        startTime = System.currentTimeMillis();
        for (int j = 0; j < loopTime; j++) {
            String str = i.toString();
        }
        System.out.println("Integer.toString()：" + (System.currentTimeMillis() - startTime) + "ms");

        // 最慢
        startTime = System.currentTimeMillis();
        for (int j = 0; j < loopTime; j++) {
            String str = i + "";
        }
        System.out.println("i + \"\"：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    @Test
    public void test1() {
        char c[] = { 'a', 'b', 'c' };
        String s = new String(c);
        System.out.println(s);
        System.out.println(s.hashCode());// 31 * i + arrayOfChar[j] 31*0+97-->31*(31*0+97)+98-->31*(31*(31*0+97)+98)+99
    }
}
