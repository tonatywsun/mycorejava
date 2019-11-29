package com.yang.polymorphic;

/**
 * @Description: 多态
 * @Author: tona.sun
 * @Date: 2019/11/20 17:05
 */
public class Test {
    public static void main(String[] args) {
        // 向上转型
        // ****编译的时候看左边（只能调用左边Animal里面的方法(非静态方法)）。
        // ****运行的时候看右边（运行结果为右边cat里面方法的结果）
        Animal mAnimal = new Cat();
        Cat mCat = new Cat();
        System.out.println("mAnimal.eat----" + mAnimal.eat());
        // 静态方法和成员变量运行结果则为左边的。
        System.out.println("mAnimal.name----" + mAnimal.name);
        System.out.println("mAnimal.nikeName----" + mAnimal.nikeName);
        System.out.println("mAnimal.getNikeName----" + mAnimal.getNikeName());
        // 不能使用猫特有的属性
        // mAnimal.play();
        // 可以这么调用. 向下转型(因为其new对象是指向的就是cat对象，所以可以这么用)
        Cat aCat = (Cat) mAnimal;
        aCat.play();
        // 下面方法则无法访问猫的私有方法,运行出错.
        Animal mAnimal2 = new Animal();
        // Cat aCat2 = (Cat) mAnimal2;
        // aCat2.play();
    }
}
