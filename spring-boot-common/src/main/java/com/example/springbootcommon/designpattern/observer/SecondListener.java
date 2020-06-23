package com.example.springbootcommon.designpattern.observer;

public class SecondListener extends Observer implements RunListener{

    public SecondListener(RunApplication application){
        this.application = application;
        this.application.regist(this);
    }

    @Override
    public void started() {
        System.out.println("second started.");
    }
}
