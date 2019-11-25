package com.yang.other.annotation.d2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* 使用@interface自定义注解时，自动继承了java.lang.annotation.Annotation接口，由编译程序自动完成其他细节。
 * 在定义注解时，不能继承其他的注解或接口。
 * @interface用来声明一个注解，其中的每一个方法实际上是声明了一个配置参数。方法的名称就是参数的名称，返回值类型就是参数的类型（返回值类型只能是基本类型、Class、String、enum）。
 * 可以通过default来声明参数的默认值。*/
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
// @Inherited表示莫类上有此注解，则其子类可以继承此注解
@Inherited
public @interface Decription {
	public String id();

	public String decription() default "我是孙阳阳";
}
