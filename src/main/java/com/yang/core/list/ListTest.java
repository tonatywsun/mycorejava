package com.yang.core.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class ListTest {
    public static void main(String[] args) {
        Collection<String> users = new ArrayList<String>();
        // Collection<String> users = new CopyOnWriteArrayList<String>();
        users.add("1");
        users.add("2");
        users.add("3");
        users.add("4");
        users.add("5");
        Iterator<String> itrUsers = users.iterator();
        while (itrUsers.hasNext()) {
            String user = (String) itrUsers.next();
            if ("4".equals(user)) {
                users.remove(user);
            } else {
                System.out.println(user);
            }
        }
    }

    @Test
    public void test1() {
        Integer[] a = { 1, 2, 3, 4, 5 };
        // 原数组-要扩容的长度
        Integer[] copyOf = Arrays.copyOf(a, 10);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(copyOf));

        // 原数组-起始位置-目标数组-目标起始位置-复制的长度
        System.arraycopy(a, 1, copyOf, 2, 1);
        System.out.println(Arrays.toString(copyOf));
    }

    @Test
    public void test2() {
        Class<Integer[]> a = Integer[].class;
        Class<Integer> b = Integer.class;
        Class<Object> o = Object.class;
        System.out.println(a.getComponentType());
        System.out.println(a);
        System.out.println(b);
        boolean c = a instanceof Object;
        boolean e = o instanceof Class;
        System.out.println(c);
        System.out.println(e);
        boolean d = (Object) a == (Object) b;
        System.out.println(d);
    }

    @Test
    public void test3() {
        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        System.out.println(stringList.getClass() == integerList.getClass());
    }

    @Test
    public void test4() {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("a");
        stringList.add("a");
        stringList.add("a");
        stringList.add("a");
        stringList.add("a");
        stringList.add("a");
        stringList.add("a");
        stringList.add("a");
        stringList.add("a");
        stringList.add("a");
        List<String> stringList2 = new ArrayList<>(5);
        stringList2.add("a");
        stringList2.add("a");
        stringList2.add("a");
        stringList.removeAll(stringList2);
        System.out.println(stringList);
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("a", "aa");
    }

}
