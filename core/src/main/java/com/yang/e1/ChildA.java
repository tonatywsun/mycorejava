package com.yang.e1;

/**
 * @ClassName: ChildA
 * @Description: 子类A继承了父类Parent，则继承了父类的全部方法，
 * 继承全部如果不能全部使用到，则会对类造成污染，如果不需要继承全部可以使用组合
 * @author: tonasun
 * @date: 2017年6月26日 下午4:28:20
 */
public class ChildA extends Parent {
    /**  
    * @Title: methodC  
    * @Description: ChildA的方法C,父类是final则无法覆盖
    * @param 
    * @return void
    * @throws 
    */  
   /* public void methodC() {
        System.out.println("i am ChildA methordC");
    }*/
    
    public final void methodD() {
        System.out.println("i am ChildA methodD");
    }
}
