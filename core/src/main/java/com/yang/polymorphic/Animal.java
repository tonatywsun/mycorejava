package com.yang.polymorphic;

/**
 * @Description: 动物类
 * @Author: tona.sun
 * @Date: 2019/11/20 17:02
 */
public class Animal {
    String name = "Animal";
    public static String nikeName = "animal";

    public static String getNikeName() {
        return nikeName;
    }

    String eat() {
        return "动物吃";
    }

    public String getName() {
        return name;
    }
}
