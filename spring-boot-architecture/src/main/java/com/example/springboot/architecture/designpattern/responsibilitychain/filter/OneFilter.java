package com.example.springboot.architecture.designpattern.responsibilitychain.filter;

import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.FilterResult;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class OneFilter implements AuditFilter{

    @Override
    public String toString(){
        return "1";
    }

    @Override
    public FilterResult doFilter(Invoker<?> invoker, AuditFilterContext filterContext) {
        System.out.println("filter1");
        return invoker.invoke(filterContext);
    }
}
