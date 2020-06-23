package com.example.springbootcommon.designpattern.observer;

public class ThirdListener extends Observer implements RunListener{

    public ThirdListener(RunApplication application){
        this.application = application;
        this.application.regist(this);
    }

    @Override
    public void started() {
        System.out.println("third started.");
    }
}
