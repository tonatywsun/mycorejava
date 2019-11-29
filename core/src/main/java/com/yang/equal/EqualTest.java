package com.yang.equal;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * equal相关测试
 *
 * @author yangyang
 * @date 2019/8/5 17:35
 */
public class EqualTest {
    @Test
    public void test() {
        EqualTestEntry e1 = new EqualTestEntry();
        EqualTestEntry e2 = new EqualTestEntry();
        Map<EqualTestEntry, String> map = new HashMap<>(8);
        //HashMap hash(key)会调用key的hashcode方法
        map.put(e1, "a");
        System.out.println("=====");
        map.put(e2, "a");
        System.out.println(map.size());
    }

}
