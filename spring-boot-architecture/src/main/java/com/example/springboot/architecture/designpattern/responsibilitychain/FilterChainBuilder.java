package com.example.springboot.architecture.designpattern.responsibilitychain;

import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;

public interface FilterChainBuilder {

    <T> Invoker<T> buildFilterChain();
}
