package com.example.springbootcommon.java8.statictest;

public class StaticOrderTest2 {

        static {
            System.out.println("SSC static");
        }

        StaticOrderTest2(){
            System.out.println("SSC construct");
        }
}
