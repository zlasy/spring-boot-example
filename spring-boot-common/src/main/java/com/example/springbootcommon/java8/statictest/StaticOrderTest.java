package com.example.springbootcommon.java8.statictest;

public class StaticOrderTest {

    static {
        i = 0;
    }
    static int i = 1;

    public static void main(String[] args) {
        System.out.println(i);
    }
}
