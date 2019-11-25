package com.yang.core.exception;

import org.junit.Test;

/**
 * try catch相关测试
 *
 * @author yangyang
 * @date 2019/8/6 10:53
 */
public class TryCatchFinallyTest {
    @Test
    public void tset1() {
        try {
            System.out.println(" tset1 try");
            int i = 5 / 0;
        } finally {
            // 不捕获上异常，finally也会被执行
            System.out.println(" tset1 finally");
        }
    }

    @Test
    public void tset2() throws Exception {
        System.out.println(" tset2 start");
        int i = 5 / 0;
        System.out.println(" tset2 end");
    }

    public static void main(String[] args) {
        //System.out.println(new TryCatchFinallyTest().testEx3());
        //tset1();
        try {
            // tset2();
            new TryCatchFinallyTest().testEx();
            System.out.println("main 123");
        } catch (Exception e) {
            System.out.println("main catch");
        }
    }

    boolean testEx() {
        boolean ret = true;
        try {
            ret = testEx1();
        } catch (Exception e) {
            System.out.println("testEx, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx, finally; return value=" + ret);
            return ret;
        }
    }

    boolean testEx1()  {
        boolean ret = true;
        try {
            ret = testEx2();
            System.out.println("testEx2 return:" + ret);
            if (!ret) {
                // 执行此句下面的则不执行
                return false;
            }
            System.out.println("testEx1, at the end of try");
            return ret;
        } catch (Exception e) {
            // 无异常，不执行
            System.out.println("testEx1, catch exception");
            ret = false;
            throw e;
        } finally {
            // 执行此处的return try中return被覆盖
            System.out.println("testEx1, finally; return value=true");
            return true;
        }
    }

    Boolean testEx2() {
        Boolean ret = true;
        //如果这里抛出了异常，下面finally不会执行，因为没有走到try块中
        /*if(ret){
            throw new RuntimeException();
        }*/
        try {
            int b = 12;
            int c;
            // 当i=0时抛出异常
            for (int i = 2; i >= -2; i--) {
                c = b / i;
                System.out.println("i=" + i);
            }
            // 此句不执行
            return true;
        } catch (Exception e) {
            System.out.println("testEx2, catch exception");
            ret = false;
            // 捕获异常之后抛出异常或者是return都会被finally中return覆盖
            //throw e;
            //此处return ret为false，虽然finally中修改了ret的值，但是finally中相当于ret = new Boolean(true);重新指向了一个对象，没有改变原对象
            //若return student finally修改studen属性，则返回值属性会被修改，见testEx3
            return ret;
        } finally {
            System.out.println("testEx2, finally; return value=" + ret);
            ret = true;
            // 执行此处return,catch中throw e或return被覆盖了不执行了
            // return ret;
        }
    }

    TryCatchFinallyTestEntry testEx3(){
        TryCatchFinallyTestEntry entry = new TryCatchFinallyTestEntry();
        try {
            entry.setAge(1);
            throw new RuntimeException();
        } catch (Exception e) {
            entry.setAge(2);
            return entry;
        } finally {
            //此处修改了entry属性，返回值会被修改
            entry.setAge(3);
        }
    }

    @Test
    public void test3() {
        int limit = Integer.MAX_VALUE >>> 2;
        int k = 0;
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 1; i < limit; i++) {
            k = 1 / i;
        }
        System.out.println("time1:" + (System.currentTimeMillis() - currentTimeMillis));

        long currentTimeMillis2 = System.currentTimeMillis();
        for (int i = 1; i < limit; i++) {
            try {
                k = 1 / i;
            } catch (Exception e) {
            }
        }
        System.out.println("time2:" + (System.currentTimeMillis() - currentTimeMillis2));

        long currentTimeMillis3 = System.currentTimeMillis();
        for (int i = 1; i < limit; i++) {
            try {
                // 发生异常时try catch比较耗时，无异常时不耗时
                k = 1 / 0;
            } catch (Exception e) {
            }
        }
        System.out.println("time3:" + (System.currentTimeMillis() - currentTimeMillis3));
    }
}
