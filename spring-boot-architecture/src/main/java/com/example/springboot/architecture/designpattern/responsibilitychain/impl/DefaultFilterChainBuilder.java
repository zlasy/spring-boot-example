package com.example.springboot.architecture.designpattern.responsibilitychain.impl;

import com.example.springboot.architecture.designpattern.responsibilitychain.AuditFilterComparator;
import com.example.springboot.architecture.designpattern.responsibilitychain.FilterChainBuilder;
import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import com.example.springboot.architecture.designpattern.responsibilitychain.extension.ExtensionLoader;
import com.example.springboot.architecture.designpattern.responsibilitychain.filter.AuditFilter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DefaultFilterChainBuilder implements FilterChainBuilder {

    @Setter
    protected Class<? extends Annotation> annotationClass;

    @Override
    public <T> Invoker<T> buildFilterChain() {
        List<AuditFilter> filters = ExtensionLoader.getExtensionLoader(AuditFilter.class).getFilterExtension();
        filters = sortingAndDeduplication(filters);
        System.out.println(filters);

        if (!CollectionUtils.isEmpty(filters)) {
            Invoker<T> last = null;
            for (int i = filters.size() - 1; i >= 0; i--) {
                final AuditFilter filter = filters.get(i);
                if (!supports(filter)) {
                    continue;
                }
                final Invoker<T> next = last;
                last = new InvokeChainNode<>(filter, next);
            }
            return last;
        }
        return null;
    }

    private boolean supports(AuditFilter filter) {
        if (annotationClass == null) {
            return true;
        }
        return filter.getClass().isAnnotationPresent(annotationClass);
    }

    private <T> List<T> sortingAndDeduplication(List<T> filters) {
        Map<Class<?>, T> filtersSet = new TreeMap<>(new AuditFilterComparator());
        for (T filter : filters) {
            filtersSet.putIfAbsent(filter.getClass(), filter);
        }
        return new ArrayList<>(filtersSet.values());
    }
}
