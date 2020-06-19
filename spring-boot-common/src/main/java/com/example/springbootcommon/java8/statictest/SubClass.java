package com.example.springbootcommon.java8.statictest;

public class SubClass extends  SuperClass{
    static {
        System.out.println("sub static");
    }

    public static final String HELLO = "hello world";

    public static int b = 2;

    public static void main(String[] args) {
        System.out.println(SubClass.a);
    }

    SubClass(){
        System.out.println("Sub construct");
    }
}
