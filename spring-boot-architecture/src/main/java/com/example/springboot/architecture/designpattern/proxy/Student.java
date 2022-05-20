package com.example.springboot.architecture.designpattern.proxy;

public class Student implements Person {

    @Override
    public void giveMoney(String name) {
        System.out.println(name + "同学");
    }

    @Override
    public void money() {
        System.out.println(".........");
    }

    public void saySth(){
        System.out.println(" just say sth.");
    }
}
