package com.example.springboot.architecture.designpattern.responsibilitychain.extension;

import com.example.springboot.architecture.designpattern.responsibilitychain.ApplicationContextHolder;
import com.example.springboot.architecture.designpattern.responsibilitychain.AuditFilterComparator;
import com.example.springboot.architecture.designpattern.responsibilitychain.filter.AuditFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ExtensionLoader<T> {

    private static final ConcurrentMap<Class<?>, ExtensionLoader<?>> EXTENSION_LOADERS = new ConcurrentHashMap<>();

    private final Class<T> type;

    private ExtensionLoader(Class<T> type) {
        this.type = type;
    }

    public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Extension type == null");
        }
        if (!type.isInterface()) {
            throw new IllegalArgumentException("Extension type (" + type + ") is not an interface!");
        }

        ExtensionLoader<T> loader = (ExtensionLoader<T>) EXTENSION_LOADERS.get(type);
        if (loader == null) {
            EXTENSION_LOADERS.putIfAbsent(type, new ExtensionLoader<T>(type));
            loader = (ExtensionLoader<T>) EXTENSION_LOADERS.get(type);
        }
        return loader;
    }


    public List<T> getFilterExtension() {
        Map<String, T> classes = getExtensionClasses();
        System.out.println(classes);
        return new ArrayList<>(classes.values());
    }

    private Map<String, T> getExtensionClasses() {
        return ApplicationContextHolder.getBeansOfType(type);
    }
}
