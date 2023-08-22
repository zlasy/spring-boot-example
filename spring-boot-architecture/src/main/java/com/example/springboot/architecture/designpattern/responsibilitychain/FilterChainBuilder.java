package com.example.springboot.architecture.designpattern.responsibilitychain;

public interface FilterChainBuilder {

    <T> Invoker<T> buildFilterChain();
}
