package com.yang.test;

/**
 * @Description: 加载顺序测试
 * @Author: tona.sun
 * @Date: 2019/11/20 17:29
 */
public class LoadOrder {
    public static void main(String[] args) {
        new E();
    }
}

class A {
    //执行完new B();之后才执行静态代码块
    static B b = new B();

    static {
        System.out.println("a staitic");
    }

    //成员变量和代码块从上往下执行，谁在上谁先执行
    C c = new C();

    {
        System.out.println("a {}");
    }

    A() {
        super();
        System.out.println("A()");
    }

}

class B {
    static {
        System.out.println("b staitic");
    }

    B() {
        System.out.println("B()");
    }

    {
        System.out.println("b {}");
    }
}

class C {
    static {
        System.out.println("c staitic");
    }

    {
        System.out.println("c {}");
    }

    public C() {
        System.out.println("C()");
    }
}

class D {
    static {
        System.out.println("d staitic");
    }

    {
        System.out.println("d {}");
    }

    public D() {
        System.out.println("D()");
    }
}

class E extends A {
    static {
        System.out.println("E static");
    }

    {
        System.out.println("E{}");
        System.out.println(this);
        setS("aaa");
    }

    D d = new D();

    // 创建E之前会先创建一个父类，所以先执行父类的成员变量和代码块
    public E() {
        super();
        System.out.println("E()");
    }

    private String s;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}