package com.yang.optional;

import java.util.Optional;

public class Main {
    public static void main(String arg[]) {
        Student student = null;//new Student();
        /*if (student == null) {
            return "null";
        }else {
            return stud.getName();
        }*/
        Optional<String> s = Optional.ofNullable(student).map(stud -> stud.getName());
        String aNull = Optional.ofNullable(student).map(stud -> stud.getName()).orElse("null");
        Student student3 = Optional.ofNullable(student).orElse(new Student());
        System.out.println(aNull);
        Optional<Student> student1 = Optional.ofNullable(student);
        //如果是null则不执行逻辑
        student1.ifPresent(student2 -> {
            System.out.println(student2);
        });
    }
}
