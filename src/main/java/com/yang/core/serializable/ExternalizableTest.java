package com.yang.core.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

/**
 * Externalizable测试
 * 
 * @author tonasun
 *
 */
public class ExternalizableTest {
	@Test
	@SuppressWarnings("all")
	public void test1() throws FileNotFoundException, IOException, ClassNotFoundException {
		// 创建一个实体类
		Student student = new Student();
		student.setAge(11);
		student.setName("yang");
		student.setSex("man");
		File file = new File("D://b.txt");
		// 写到文件里
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
		objectOutputStream.writeObject(student);
		// 读出来
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
		Student readObject = (Student) objectInputStream.readObject();
		System.out.println(readObject);
	}
}
