package com.example.springbootcommon.quiz;

public class ClassA {

    static {
        A = "base block";
        System.out.println("A static block");
    }

    public static String A = "base";
}