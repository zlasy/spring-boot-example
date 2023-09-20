package com.example.springboot.architecture.designpattern.responsibilitychain.factory;

import com.example.springboot.architecture.designpattern.responsibilitychain.FilterChainBuilder;
import com.example.springboot.architecture.designpattern.responsibilitychain.impl.DefaultFilterChainBuilder;
import com.example.springboot.architecture.designpattern.responsibilitychain.impl.SpecialDeliverFilterChainBuilder;
import org.springframework.stereotype.Service;

@Service
public class FilterChainBuilderFactory {

    public FilterChainBuilder getFilterChainBuilder(String type) {
        switch (type) {
            case "special":
                return new SpecialDeliverFilterChainBuilder();
            case "default":
                return new DefaultFilterChainBuilder();
            default:
                return null;
        }
    }

}
