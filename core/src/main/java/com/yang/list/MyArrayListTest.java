package com.yang.list;

public class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("b");
        list.add("a");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        System.out.println(list.size());
        list.remove("a");
        for(int i =0;i<list.size();i++) {
            System.out.print(list.get(i));
        }
    }
}
