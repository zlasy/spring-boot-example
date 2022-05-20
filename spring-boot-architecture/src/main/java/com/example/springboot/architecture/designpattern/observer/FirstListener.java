package com.example.springboot.architecture.designpattern.observer;

public class FirstListener extends Observer implements RunListener{

    public FirstListener(RunApplication application){
        this.application = application;
        this.application.regist(this);
    }

    @Override
    public void started() {
        System.out.println("first started.");
    }
}
