package com.yang.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: tona.sun
 * @Date: 2020/06/04 14:35
 */
public class 循环方法 {
    public static void main(String[] args) {
        for (String s : getList()) {
            System.out.println(s);
        }

    }

    public static List<String> getList() {
        System.out.println("getlist");
        ArrayList<String> result = new ArrayList<>();
        result.add("1");
        result.add("2");
        return result;
    }
}
