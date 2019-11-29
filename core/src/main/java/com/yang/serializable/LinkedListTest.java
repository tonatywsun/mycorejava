package com.yang.serializable;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class LinkedListTest {
	@Test
	@SuppressWarnings("all")
	public void test1() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File("D://a.txt");
		LinkedList<String> list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
		objectOutputStream.writeObject(list);
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
		LinkedList<String> readObject = (LinkedList<String>) objectInputStream.readObject();
		System.out.println(readObject.size());
	}

	/**
	 * 自己写了一个entry类，元素是transient修饰，写到流中读取出来之后数据丢失。但是LinkedList的size为什么没有丢失？
	 * 因为LinkedList中writeObject和readObject方法在序列化和反序列化的时候会执行 会赋值
	 */
	@Test
	@SuppressWarnings("all")
	public void test2() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File("D://b.txt");
		TransientDemo transientTest = new TransientDemo();
		transientTest.setAge(11);
		transientTest.setName("yang");
		transientTest.setSex("man");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
		objectOutputStream.writeObject(transientTest);
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
		TransientDemo readObject = (TransientDemo) objectInputStream.readObject();
		System.out.println(readObject);
	}
}
