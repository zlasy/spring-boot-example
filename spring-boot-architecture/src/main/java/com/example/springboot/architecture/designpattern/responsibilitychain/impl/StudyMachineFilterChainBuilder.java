package com.example.springboot.architecture.designpattern.responsibilitychain.impl;

import com.example.springboot.architecture.designpattern.responsibilitychain.FilterChainBuilder;
import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import org.springframework.stereotype.Service;

@Service
public class StudyMachineFilterChainBuilder implements FilterChainBuilder {
    @Override
    public <T> Invoker<T> buildFilterChain() {
        System.out.println("nothing happens");
        return null;
    }
}
