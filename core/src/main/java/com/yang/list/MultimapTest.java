package com.yang.list;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

import java.util.Collection;

/**
 * @Description: TODO
 * @Author: tona.sun
 * @Date: 2020/08/06 10:19
 */
public class MultimapTest {
    public static void main(String[] args) {
        Multimap<String, Integer> map = HashMultimap.create();
        map.put("1", 1);
        map.put("1", 2);
        System.out.println(map.toString());
        Collection<Integer> integers = map.get("1");

        Multiset<Integer> set = HashMultiset.create();
        set.add(1);
        set.add(1);
        System.out.println(set.toString());
        for (Multiset.Entry<Integer> i : set.entrySet()) {
            System.out.println(i.getElement());
            System.out.println(i.getCount());
            System.out.println(set.count(i.getElement()));
        }
    }
}
