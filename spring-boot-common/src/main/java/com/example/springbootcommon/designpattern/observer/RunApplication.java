package com.example.springbootcommon.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class RunApplication {

    private List<RunListener> listeners = new ArrayList<>();

    void regist(RunListener listener){
        this.listeners.add(listener);
    }

    public void started(){
        for (RunListener listener: listeners) {
            listener.started();
        }
    }

    public static void main(String[] args) {
        RunApplication application = new RunApplication();
        FirstListener first = new FirstListener(application);
        SecondListener second = new SecondListener(application);
        ThirdListener third = new ThirdListener(application);

        application.started();
    }
}
