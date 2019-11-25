package com.yang.core.polymorphic;

/**
 * @Description: 猫类继承动物类
 * @Author: tona.sun
 * @Date: 2019/11/20 17:02
 */
public class Cat extends Animal {
    String name = "Cat";

    public static String nikeName = "cat";

    public static String getNikeName() {
        return nikeName;
    }

    @Override
    String eat() {
        return "猫吃";
    }

    void play() {
        System.out.println("猫抓老鼠");
    }

    @Override
    public String getName() {
        return name;
    }
}
