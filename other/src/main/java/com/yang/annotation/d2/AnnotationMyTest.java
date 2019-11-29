package com.yang.annotation.d2;

import java.lang.reflect.Method;

public class AnnotationMyTest {
	public static void main(String[] args) {
		try {
			// 通过反射扫描这个类
			Class<?> c = Class.forName("MyselfTest.annotationTest.ActiveExtend");
			// isAnnotationPresent判断该类(该方法)是否含有次注解
			boolean isExist = c.isAnnotationPresent(Decription.class);
			if (isExist) {
				Decription d = c.getAnnotation(Decription.class);
				System.out.println("1:" + d.id() + d.decription());
			}

			Method[] ms = c.getMethods();
			for (Method m : ms) {
				boolean isExistMothed = m.isAnnotationPresent(Decription.class);
				if (isExistMothed) {
					Decription d = m.getAnnotation(Decription.class);
					System.out.println("2:" + d.id() + d.decription());
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
