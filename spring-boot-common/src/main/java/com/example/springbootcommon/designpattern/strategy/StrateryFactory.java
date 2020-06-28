package com.example.springbootcommon.designpattern.strategy;

import java.util.HashMap;
import java.util.Map;

public class StrateryFactory {

    private Map<String, ChoiceStrategy> strategyMap;

    StrateryFactory(){
        strategyMap = new HashMap<>();
        strategyMap.put("1", new StrategyOne());
        strategyMap.put("2", new StrategyTwo());
    }

    public void put(String key, ChoiceStrategy cs){
        strategyMap.put(key, cs);
    }

    public ChoiceStrategy get(String key){
        return strategyMap.get(key);
    }

    public void choice(String key){
        get(key).choice();
    }

    public static void main(String[] args) {
        StrateryFactory sf = new StrateryFactory();
        sf.choice("1");
        sf.choice("2");
    }
}
