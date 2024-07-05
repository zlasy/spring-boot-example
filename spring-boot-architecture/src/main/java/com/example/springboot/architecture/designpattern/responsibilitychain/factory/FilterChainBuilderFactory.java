package com.example.springboot.architecture.designpattern.responsibilitychain.factory;

import com.example.springboot.architecture.designpattern.responsibilitychain.FilterChainBuilder;
import com.example.springboot.architecture.designpattern.responsibilitychain.impl.DefaultFilterChainBuilder;
import com.example.springboot.architecture.designpattern.responsibilitychain.impl.SpecialDeliverFilterChainBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class FilterChainBuilderFactory {

    private final ConcurrentMap<String, FilterChainBuilder> cachedBuilders = new ConcurrentHashMap<>();

    public FilterChainBuilder getFilterChainBuilder(String type) {
        if (!cachedBuilders.containsKey(type)) {
            synchronized (FilterChainBuilder.class) {
                if (!cachedBuilders.containsKey(type)) {
                    FilterChainBuilder builder = createFilterChainBuilder(type);
                    cachedBuilders.put(type, builder);
                }
            }
        }
        return cachedBuilders.get(type);
    }

    private FilterChainBuilder createFilterChainBuilder(String type) {
        switch (type) {
            case "special":
                return new SpecialDeliverFilterChainBuilder();
            case "default":
                return new DefaultFilterChainBuilder();
            default:
                throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }

}
