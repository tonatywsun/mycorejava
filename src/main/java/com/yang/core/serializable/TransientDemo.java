package com.yang.core.serializable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;

/**
 * 实现Serializable测试类
 * @author yangyang
 * @date 2019/8/24 23:43
 */
public class TransientDemo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * @Fields transient修饰的字段在序列化的时候不会被序列化
     */
    private transient int age;
    private transient String name;
    private String sex;

    /**
     * 如果同时定义了ObjectStreamField与transient，transient会被忽略。
     * serialPersistentFields只会序列化其中字段，其为空则全部不序列化，其为null则相当于没写
     * readObject/writeObject优先级大于它
     */
    private static final ObjectStreamField[]
            serialPersistentFields = {new ObjectStreamField("name", String.class)};

    public TransientDemo(int age, String name) {
        super();
        System.out.println("TransientTest(int age, String name)");
        this.age = age;
        this.name = name;
    }

    public TransientDemo() {
        super();
        System.out.println("TransientTest");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("setAge");
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setName");
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        System.out.println("setSex");
        this.sex = sex;
    }

    /**
     * 序列化写入的时候会执行此方法(自定义序列化)
     * ObjectOutputStream使用了反射来寻找是否声明了这两个方法。因为ObjectOutputStream使用getPrivateMethod，所以这些方法不得不被声明为priate以至于供ObjectOutputStream来使用。
     */
    private void writeObject(ObjectOutputStream s) throws java.io.IOException {
        System.out.println("writeObject");
        s.defaultWriteObject();
        s.writeInt(age);
        s.writeInt(111);
        s.writeObject(name);
        s.writeObject("2");
    }

    /**
     * 反序列化读取的时候会执行此方法 自定义序列化方式
     */
    private void readObject(ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
        System.out.println("readObject");
        s.defaultReadObject();
        setAge(s.readInt());
        int readInt2 = s.readInt();
        System.out.println(readInt2);
        setName((String) s.readObject());
        System.out.println(s.readObject());
    }

    @Override
    public String toString() {
        return "TransientTest [age=" + age + ", name=" + name + ", sex=" + sex + "]";
    }

}
