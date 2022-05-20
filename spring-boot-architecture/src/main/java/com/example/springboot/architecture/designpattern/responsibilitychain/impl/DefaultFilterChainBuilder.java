package com.example.springboot.architecture.designpattern.responsibilitychain.impl;

import com.example.springboot.architecture.designpattern.responsibilitychain.ApplicationContextHolder;
import com.example.springboot.architecture.designpattern.responsibilitychain.AuditFilterComparator;
import com.example.springboot.architecture.designpattern.responsibilitychain.FilterChainBuilder;
import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import com.example.springboot.architecture.designpattern.responsibilitychain.filter.AuditFilter;
import com.example.springboot.architecture.designpattern.responsibilitychain.filter.FilterChainNode;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class DefaultFilterChainBuilder implements FilterChainBuilder {

    private Invoker first;

    @Override
    public <T> Invoker<T> buildFilterChain() {
        List<AuditFilter> filters = new ArrayList<>();
        Map<String, AuditFilter> map = ApplicationContextHolder.getBeansOfType(AuditFilter.class);
        filters.addAll(map.values());
        filters = sortingAndDeduplication(filters);
        System.out.println(filters);

        if (!CollectionUtils.isEmpty(filters)) {
            Invoker<T> last = null;
            for (int i = filters.size() - 1; i >= 0; i--) {
                final AuditFilter filter = filters.get(i);
                final Invoker<T> next = last;
                last = new FilterChainNode<>(filter, next);
            }
            first = last;
        }

        return first;
    }

    private <T> List<T> sortingAndDeduplication(List<T> filters) {
        Map<Class<?>, T> filtersSet = new TreeMap<>(new AuditFilterComparator());
        for (T filter : filters) {
            filtersSet.putIfAbsent(filter.getClass(), filter);
        }
        return new ArrayList<>(filtersSet.values());
    }
}
