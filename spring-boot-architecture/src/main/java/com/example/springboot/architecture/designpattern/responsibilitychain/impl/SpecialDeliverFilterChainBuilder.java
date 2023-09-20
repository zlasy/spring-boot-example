package com.example.springboot.architecture.designpattern.responsibilitychain.impl;

import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import com.example.springboot.architecture.designpattern.responsibilitychain.annotation.SpecialDeliverFilter;

import java.lang.annotation.Annotation;

public class SpecialDeliverFilterChainBuilder extends DefaultFilterChainBuilder{

    public SpecialDeliverFilterChainBuilder() {
        this.setAnnotationClass(SpecialDeliverFilter.class);
    }

    public <T> Invoker<T> buildFilterChain() {
        return super.buildFilterChain();
    }
}
