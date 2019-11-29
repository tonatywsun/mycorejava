package com.yang.string;

public class 字符串比较 {
    public static void main(String[] args) {

        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";

        String s4 = "ab";
        // 根据String的概念他们都指向了同一个内存池内的地址(指向同一个对象)，所以结果为true。
        System.out.println("s3==s4 -> " + (s3 == s4));

        String s5 = "a" + "b";
        StringBuilder sb = new StringBuilder();
        sb.append("a");
        sb.append("b");
        String s51 = sb.toString();
        // 会查找常量池中时候存在内容为"ab"字符串对象，如存在则直接让s5引用该对象
        // 因为相加的两个为常量，所以编译器会把s5="a"+"b"优化为s5="ab"，所以结果也为true。
        System.out.println("s3==s5 -> " + (s3 == s5));
        System.out.println("s3==s51 -> " + (s3 == s51));

        String s6 = s1 + s2;
        // 因为是两个变量的相加所以编译器无法优化，s1+s2即等同于(new StringBuilder(String.valueOf(s1))).append(s2).toString();
        // 在运行时，会有新的String地址空间的分配，而不是指向缓冲池中的“ab”。所以结果false。
        System.out.println("s3==s6 -> " + (s3 == s6));
        System.out.println("s51==s6 -> " + (s51 == s6));

        String s61 = "ab";
        String s62 = s1 + s2;
        System.out.println("s3==s61 -> " + (s3 == s61));
        System.out.println("s3==s62 -> " + (s3 == s62));
        System.out.println("s6==s61 -> " + (s6 == s61));
        System.out.println("s6==s62 -> " + (s6 == s62));

        String s7 = new String("ab");
        // 根据缓冲池的定义在new的时候实际会新分配地址空间，s7指向的是新分配的地址空间所以与缓冲池地址不同，所以为false。
        System.out.println("s3==s7 -> " + (s3 == s7));

        final String s8 = "a";
        final String s9 = "b";
        String s10 = s8 + s9;
        // 类似于s3与s5，因为是final类型编译器进行了优化所以相同。
        System.out.println("s3==s10 -> " + (s3 == s10));

        // new String()创建了一个空字符串而不是null
        System.out.println("new String()" + new String());
    }
}
