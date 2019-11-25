package com.yang.core.serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Serializable序列化时不会调用默认的构造器，而Externalizable序列化时会调用默认构造器的！！！
 * Serializable：一个对象想要被序列化，那么它的类就要实现此接口，这个对象的所有属性（包括private属性、包括其引用的对象）都可以被序列化和反序列化来保存、传递。
 * Externalizable：他是Serializable接口的子类，有时我们不希望序列化那么多，可以使用这个接口，这个接口的writeExternal()和readExternal()方法可以指定序列化哪些属性。
 * 
 * @date 2018年5月31日 上午11:21:59
 * @author tonasun
 */
public class Student implements Externalizable {
	private int age;
	private String name;
	private static String sex;

	/**
	 * 写哪些属性
	 */
	@Override
	public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
		paramObjectOutput.writeInt(age);
	}

	/**
	 * 读哪些属性
	 */
	@Override
	public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
		setAge(paramObjectInput.readInt());
	}

	public Student() {
		super();
		System.out.println("ExternalizableTestEntry()");
	}

	public Student(int age, String name) {
		super();
		this.age = age;
		this.name = name;
		System.out.println("ExternalizableTestEntry(int age, String name)");
	}

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

	public static String getSex() {
		return sex;
	}

	public static void setSex(String sex) {
		Student.sex = sex;
	}

	@Override
	public String toString() {
		return "Student [age=" + age + ", name=" + name + "]";
	}

}
