package com.yang.test;

public class 可变参数 {
	public static void main(String[] args) {
		int i = 1;// int--Object--int i, char... b--int...
		Integer ii = 1;// Object--int--int i, char... b--int...
		new 可变参数().f(ii);
	}

	public void f(int... a) {
		System.out.println("int...");
	}

	public void f(int i, char... b) {
		System.out.println("int i, char... b");
	}

	public void f(int i) {
		System.out.println("int");
	}

	public void f(Object o) {
		System.out.println("Object");
	}

}
