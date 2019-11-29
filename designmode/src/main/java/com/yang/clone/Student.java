package com.yang.clone;

/**
 * 必须实现Cloneable接口。
 * 在java语言有一个Cloneable接口，它的作用只有一个，就是在运行时通知虚拟机可以安全地在实现了此接口的类上使用clone方法。
 * 在java虚拟机中，只有实现了这个接口的类才可以被克隆，否则在运行时会抛出CloneNotSupportedException异常。
 *
 * @author: tonasun
 * @date: 2017年4月12日 上午10:47:48
 */
public class Student implements Cloneable {
    private Integer age;
    private String name;
    /**
     * @fieldType: Teacher
     * @Description: TODO(每个学生有一个老师)
     */
    private Teacher teacher;

    public Student() {
        System.out.println("执行Student构造函数！");
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /*
     * clone()方法是定义在java.lang.Object类中，该方法是一个protected的方法，
     * 所以重载时要把clone()方法的属性设置为public，这样其它类才能调用这个clone类的clone()方法
     */
    @Override
    public Student clone() {
        Student student = null;
        try {
            /*
             * 浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
             * 深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。
             */
            student = (Student) super.clone();
            // 深克隆需要重写clone方法，为引用属性进行克隆，引用属性也要实现Cloneable接口并且显示重写clone（）方法，使外部能调用到此方法
            // 对引用类型的域进行克隆(深克隆)，浅克隆被克隆对象与克隆出的对像中的引用属性指向同一个引用对象。而深克隆中的引用属性则不指向同一对象，是原引用属性对象的克隆。
            // student.teacher =
            // teacher.clone();//(因为此处对teacher进行了克隆，所以teacher要是可克隆的。如果此处不对teacher进行克隆则teacher无需可克隆)
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public String toString() {
        return "Student{" + "age=" + age + ", name='" + name + '\'' + ", teacher=" + teacher + '}';
    }
}
