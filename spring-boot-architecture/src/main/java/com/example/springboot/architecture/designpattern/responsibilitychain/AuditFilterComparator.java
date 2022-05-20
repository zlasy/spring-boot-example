package com.example.springboot.architecture.designpattern.responsibilitychain;

import lombok.Data;
import org.springframework.core.annotation.Order;

import java.util.Comparator;

public class AuditFilterComparator implements Comparator<Class<?>> {

    @Override
    public int compare(Class o1, Class o2) {
        int i1 = 2147483647;
        int i2 = 2147483647;
        if (o1.isAnnotationPresent(Order.class)) {
            Order order = (Order) o1.getAnnotation(Order.class);
            i1 = order.value();
        }
        if (o2.isAnnotationPresent(Order.class)) {
            Order order2 = (Order) o2.getAnnotation(Order.class);
            i2 = order2.value();
        }
        return i1 > i2 ? 1 : -1;
    }
}
