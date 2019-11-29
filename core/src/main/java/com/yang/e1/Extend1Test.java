package com.yang.e1;

public class Extend1Test {
	public static void main(String[] args) {
		// 通过这两种方法都能使用到Parent类中的A方法，但是B中比A中多一个方法，可以根据需求选择继承和组合
		ChildA a = new ChildA();
		ChildB b = new ChildB();
		a.methodA();
		a.methodC();
		a.methodD();
		b.methodA();
	}
}
