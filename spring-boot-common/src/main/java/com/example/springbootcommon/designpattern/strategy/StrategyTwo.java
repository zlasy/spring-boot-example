package com.example.springbootcommon.designpattern.strategy;

public class StrategyTwo implements ChoiceStrategy{
    @Override
    public void choice() {
        System.out.println("choose two");
    }
}
