package com.yang.equal;

import java.util.Objects;

/**
 * 随便写个实体类
 *
 * @Author:yang
 * @Date: 2019/8/5 17:42
 */
public class EqualTestEntry {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("equalTestEntry equals");
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EqualTestEntry entry = (EqualTestEntry) o;
        return age == entry.age && name == null ? entry.name == null : name.equals(entry.name);
    }

    @Override
    public int hashCode() {
        System.out.println(" equalTestEntry hashCode");
        return Objects.hash(age, name);
    }

    /*这样可以实现hashCode不同equals返回true,但是不符合规矩
    @Override
    public boolean equals(Object o) {
        return true;
    }

    public static int i = 0;

    @Override
    public int hashCode() {
        return i++;
    }*/
}
