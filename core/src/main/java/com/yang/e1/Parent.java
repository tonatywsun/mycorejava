package com.yang.e1;

/**
 * @ClassName: Parent
 * @Description: TODO(父类Parent)
 * @author: tonasun
 * @date: 2017年6月26日 下午4:27:04
 */
public class Parent {
    /**
     * @Title: methodA @Description: TODO(父类方法A) @param @return void @throws
     */
    public void methodA() {
        System.out.println("i am parent methordA");
    }

    /**
     * @Title: methodB @Description: TODO(父类方法B) @param @return void @throws
     */
    public void methodB() {
        System.out.println("i am parent methordB");
    }

    public final void methodC() {
        System.out.println("i am parent private methodC");
    }

    /**  
    * @Description: private 子类无法继承，所以也不存在覆盖
    * @param 
    * @return void
    * @throws  
    */  
    private final void methodD() {
        System.out.println("i am parent private final methodD");
    }
}
