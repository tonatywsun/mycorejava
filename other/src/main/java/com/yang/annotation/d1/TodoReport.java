package com.yang.annotation.d1;

import java.lang.reflect.Method;

public class TodoReport {
	public TodoReport() {
		super();
	}

	public static void main(String[] args) {
		getTodoReportForBusinessLogic();
	}

	private static void getTodoReportForBusinessLogic() {
		Class<BusinessLogic> businessLogicClass = BusinessLogic.class;
		for (Method method : businessLogicClass.getMethods()) {
			Todo todoAnnotation = (Todo) method.getAnnotation(Todo.class);
			if (todoAnnotation != null) {
				System.out.println(" Method Name : " + method.getName());
				System.out.println(" Author : " + todoAnnotation.author());
				System.out.println(" Priority : " + todoAnnotation.priority());
				System.out.println(" Status : " + todoAnnotation.status());
				System.out.println(" --------------------------- ");
			}
		}
	}
}
