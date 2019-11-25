package com.yang.core.extend.e1;

/**
 * @ClassName: ChildB
 * @Description: 与Parent类组合，仅使用Parent中的methodA方法，
 * 而不使用B方法避免继承到过多用不到（不需要）的东西污染了类
 * @author: tonasun
 * @date: 2017年6月26日 下午4:26:33
 */
public class ChildB {
    Parent p = new Parent();

    /**  
    * @Title: methodA  
    * @Description: TODO(通过组合使用父类的A方法) 
    * @param 
    * @return void
    * @throws 
    */  
    public void methodA() {
        p.methodA();
    }

    public void methodC() {
        System.out.println("i am ChildB methordC");
    }
}
