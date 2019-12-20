package com.yang.optional;

import java.util.Optional;

public class Main {
    public static void main(String arg[]) {
        Student student = null;
        /*if (student == null) {
            return "null";
        }else {
            return stud.getName();
        }*/
        String aNull = Optional.ofNullable(student).map(stud -> stud.getName()).orElse("null");
        System.out.println(aNull);
    }
}
