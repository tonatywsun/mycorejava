package com.yang.set;

/**
 * @Description: TODO
 * @Author: tona.sun
 * @Date: 2020/04/09 15:53
 */
public class Parent {
    void a() {
        System.out.println("p a");
    }

    public void b() {
        System.out.println("p b");
        //这里就调用了子类的a();
        a();
    }

    public static void main(String[] args) {
        Parent p = new Child();
        //p b
        //c a
        p.b();
    }
}
