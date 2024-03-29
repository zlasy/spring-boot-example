package com.example.springboot.architecture.designpattern.singleton;

public class Staticinternalclass {

    private static class SingletonHolder {
        private static final Staticinternalclass INSTANCE = new Staticinternalclass();
    }

    private Staticinternalclass(){}

    public static Staticinternalclass getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
