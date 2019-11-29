package com.yang.interfacefuction.function;

import org.junit.Test;

/**
 * @author yangyang
 * @date 2019/8/21 14:35
 */
public class MyTest {
    public static void main(String[] args) {
        new MyTest().test1();
        new MyTest().test2();
    }

    @Test
    public void test1() {
        //这样写是不是很麻烦
        FuncInterface funInterface = new FuncInterface() {
            @Override
            public String fun(String s) {
                System.out.println(s);
                return s + "匿名内部类";
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }

            @Override
            public String toString() {
                return null;
            }
        };
        funInterface.fun("匿名内部类");
        //简化 lamada表达式
        FuncInterface funInterface2 = (s) -> {
            System.out.println(s);
            return s + "lamabda表达式";
        };
        funInterface.fun("lamada表达式");
        //这种更简单，等于直接return
        FuncInterface funInterface3 = (s) -> s + "lamada表达式";
        funInterface3.fun("lamada表达式");
    }

    @Test
    public void test2() {
        SmsService sendSmsService = new SmsService();
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendSmsService.send();
            }
        }).start();

        //Runnable也是一个函数接口  所以可以使用lamaba表达式
        new Thread(() -> {
            sendSmsService.send();
        }).start();

        //更简单
        Runnable sendsms = sendSmsService::send;
        new Thread(sendsms).start();
        //
        new Thread(sendSmsService::send).start();
    }
}
