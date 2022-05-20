package com.example.springboot.architecture.designpattern.responsibilitychain.filter;

import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.FilterResult;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component
public class ThreeFilter implements AuditFilter{
    @Override
    public String toString(){
        return "3";
    }

    @Override
    public FilterResult doFilter(Invoker<?> invoker, AuditFilterContext filterContext) {
        System.out.println("filter3");
        if (invoker == null) {
            return FilterResult.success();
        }
        return invoker.invoke(filterContext);
    }
}
