package com.example.springboot.architecture.designpattern.strategy;

public class StrategyOne implements ChoiceStrategy{
    @Override
    public void choice() {
        System.out.println("choose one");
    }
}
