package com.yang.clone;

/**
 * @ClassName: Teacher
 * @Description: TODO(clone测试类)
 * @author: tonasun
 * @date: 2017年4月12日 上午10:48:36
 */
public class Teacher implements Cloneable {
    private Integer age;
    private String name;

    public Teacher() {
        System.out.println("执行Teacher构造器！");
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Teacher clone() {
        Teacher teacher = null;
        try {
            teacher = (Teacher) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
