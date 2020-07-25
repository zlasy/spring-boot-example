package com.example.springbootcommon.designpattern.factory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ChatFactory implements InitializingBean {

    public final String t;

    static {
        System.out.println("static section");
    }

    ChatFactory(){
        System.out.println("constructor");
        t = "t2";
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("afterPropertiesSet");
    }

    @PostConstruct
    private void init(){
        System.out.println("PostConstruct");
    }
}
