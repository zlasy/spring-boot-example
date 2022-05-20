package com.example.springboot.architecture.designpattern.singleton;

public class Doublecheck {

    /**  这个volatile可以避免隐患吗？ */
    private volatile static Doublecheck instance;

    private Doublecheck(){};

    public static Doublecheck getInstance(){
        if(instance == null){
            synchronized (Doublecheck.class){
                if(instance == null){
                    // 下面这行有隐患
                    instance = new Doublecheck();
                }
            }
        }
        return instance;
    }
}
