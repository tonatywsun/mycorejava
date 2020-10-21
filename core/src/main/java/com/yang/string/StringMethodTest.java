package com.yang.string;

import org.junit.Test;

/**
 * @author tona.sun
 * @version V1.0
 * @className: StringMethodTest
 * @description: 字符串方法测试
 * @date 2020/10/21 16:34
 */
public class StringMethodTest {
    @Test
    public void trimTest() {
        String a = " 1 2 3 ";
        System.out.println(a.trim());
        //去除首尾空格
        System.out.println(a.strip());
        // 去除尾部空格
        System.out.println(a.stripLeading());
        // 去除尾部空格
        System.out.println(a.stripTrailing());
        // 复制字符串
        System.out.println(a.repeat(3));
    }
}
