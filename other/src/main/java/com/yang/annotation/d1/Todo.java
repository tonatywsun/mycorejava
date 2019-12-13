/*
 * This code demonstrates how to define an annotation.
 * 
 * @author Yashwant Golecha (ygolecha@gmail.com)
 * @version 1.0
 * 
 */

package com.yang.annotation.d1;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//指定注解出点的地方
/**
 * 1、RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；
 * 2、RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；
 * 3、RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 */
@Retention(RetentionPolicy.RUNTIME)
@interface Todo {
    public enum Priority {LOW, MEDIUM, HIGH}
    public enum Status {STARTED, NOT_STARTED}    
    String author() default "Yash";
    Priority priority() default Priority.LOW;
    Status status() default Status.NOT_STARTED;
}
