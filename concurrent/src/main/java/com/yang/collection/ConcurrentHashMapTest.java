package com.yang.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: TODO
 * @Author: tona.sun
 * @Date: 2019/12/05 21:28
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap();
        map.put("a", "a");
    }
}
