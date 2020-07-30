package com.yang.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @Description: 试试set
 * @Author: tona.sun
 * @Date: 2020/04/09 10:23
 */
public class SetTest {
    public static void main(String[] args) {
        // set无序不可重复 但LinkedHashSet是有序的，but也是不可重复，因为它使用了的LinkedHashMap的key存储数据，key是不可重复的
        /*Linked*/
        HashSet<String> set1 = new /*Linked*/HashSet<>();
        set1.add("3");
        set1.add("4");
        set1.add("2");
        set1.add("1");
        set1.add("5");
        set1.add("5");
        //LinkedKeyIterator
        for (Iterator<String> iterator = set1.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
        //这两个for循环原理一样
        for (String s : set1) {
            System.out.println(s);
        }
        //LinkedHashMap是HashMap的子类 通过重写父类方法维护了一个Linked
        //重写newNode改变了node使每个node都有next和pre
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("1", "1");
        linkedHashMap.get("1");
    }
}
