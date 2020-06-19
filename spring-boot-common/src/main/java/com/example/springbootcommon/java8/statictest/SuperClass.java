package com.example.springbootcommon.java8.statictest;

public class SuperClass extends StaticOrderTest2{
    static {
        System.out.println("Super static");
    }

    public static int a = 1;

    SuperClass(){
        System.out.println("Super construct");
    }
}
