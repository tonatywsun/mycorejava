package com.yang.clone;

import org.junit.Test;

import java.util.HashMap;

/**
 * @ClassName: CloneTest
 * @Description: clone测试类, 使用原型模式创建对象比直接new一个对象在性能上要好的多，
 * 因为Object类的clone方法是一个本地方法，它直接操作内存中的二进制流，特别是复制大对象时，性能的差别非常明显。
 * @author: tonasun
 * @date: 2017年4月12日 上午10:48:50
 */
public class CloneTest {
    /* *
     * clone模式测试
     * @param args
     * @return:void
     * @Author:yang
     * @Date: 2019/8/5 16:04
     */
    public static void main(String[] args) {
        Integer age = 30;
        System.out.println("创建一个教师类！");
        Teacher teacher = new Teacher();
        teacher.setName("teacher sun");
        teacher.setAge(age);
        System.out.println("======================");

        System.out.println("创建一个学生类！");
        Student student = new Student();
        student.setTeacher(teacher);
        student.setAge(20);
        student.setName("student sun");
        System.out.println("======================");
        /*
         * 使用原型模式复制对象不会调用类的构造方法。因为对象的复制是通过调用Object类的clone方法来完成的，它直接在内存中复制数据，
         * 因此不会调用到类的构造方法。 不但构造方法中的代码不会执行，甚至连访问权限都对原型模式无效。
         * 单例模式中，只要将构造方法的访问权限设置为private型，就可以实现单例。但是clone方法直接无视构造方法的权限，所以，单例模式与原型模式是冲突的，
         * 在使用时要特别注意。
         */
        System.out.println("克隆一个教师");
        Teacher cloneTeacher = teacher.clone();
        System.out.println("teacher:" + teacher);
        System.out.println("cloneTeacher:" + cloneTeacher);

        System.out.println("======================");
        System.out.println("克隆一个学生");
        Student cloneStudent = student.clone();
        System.out.println("student" + student);
        System.out.println("cloneStudent" + cloneStudent);
        Teacher cloneStudentTeacher = student.getTeacher();
        boolean isDD = teacher == cloneStudentTeacher;
        System.out.println("teacher==cloneStudentTeacher:" + isDD);

        System.out.println("======================");
        System.out.println("现在改变老师的名字为new teacher sun");
        teacher.setName("new teacher sun");
        // 方法参数为基本类型,则在栈内存中开辟新的空间，所有的方法体内部的操作都是针对这个拷贝的操作，并不会影响原来输入实参的值。

        System.out.println("======================");
        //teacher与cloneTeacher不指向同一个对象,所以此处不会改变
        System.out.println("teacher改名字之后cloneTeacher:" + cloneTeacher);
        // 若方法参数为引用类型,该拷贝与输入实参指向了同一个对象，方法体内部对于对象的操作，都是针对的同一个对象(浅克隆)
        // 深克隆时引用对象的克隆也会克隆出一个新的对象，原对象teacher更改名字之后cloneStudent中的teacher不变(不指向同一个对象)
        // 将student类中clone方法中的student.teacher = teacher.clone();放开即可看到深克隆与浅克隆的区别！
        System.out.println("teacher改名字之后cloneStudent:" + cloneStudent);
        System.out.println("teacher改名字之后student:" + student);
    }

    /**
     * @Title: mapTest
     * @Description: HashMap实现了Cloneable, Serializable接口，所以是可以被克隆的，默认是浅克隆，重写为深克隆
     */
    @Test
    @SuppressWarnings("unchecked")
    public void mapTest() {
        HashMap<String, Teacher> map = new HashMap<String, Teacher>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Object clone() {
                HashMap<String, Teacher> map = (HashMap<String, Teacher>) super.clone();
                for (String s : map.keySet()) {
                    Teacher teacher = map.get(s);
                    map.put(s, teacher.clone());
                }
                return map;
            }
        };
        Teacher teacher = new Teacher();
        Teacher teacher2 = new Teacher();
        teacher.setName("teacher sun");
        teacher2.setName("teacher sun2");
        map.put("1", teacher);
        map.put("2", teacher2);
        HashMap<String, Teacher> mapClone = (HashMap<String, Teacher>) map.clone();
        System.out.println(mapClone.get("1"));
        System.out.println(mapClone.get("2"));
        teacher.setName("new teacher sun");
        teacher2.setName("new teacher sun2");
        System.out.println(mapClone.get("1"));
        System.out.println(mapClone.get("2"));
    }

}
