package com.yang.number;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberBigDecimal {
    public static void main(String[] args) {
        //禁止使用BigDecimal的equals方法做等值比较，推荐使用compareTo方法
        BigDecimal b1 = new BigDecimal("1.0");
        BigDecimal b2 = new BigDecimal("1");
        //System.out.println(b1.equals(b2));

        BigDecimal b3 = new BigDecimal("1.0");
        BigDecimal b4 = b3.setScale(5, RoundingMode.FLOOR);
        System.out.println(b3);
        System.out.println(b4);
    }
}
