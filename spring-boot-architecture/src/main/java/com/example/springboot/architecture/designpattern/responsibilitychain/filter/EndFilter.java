package com.example.springboot.architecture.designpattern.responsibilitychain.filter;

import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.FilterResult;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(99999)
@Component
public class EndFilter implements AuditFilter{
    @Override
    public String toString(){
        return "end";
    }

    @Override
    public FilterResult doFilter(Invoker<?> invoker, AuditFilterContext filterContext) {
        System.out.println("filter-end");
        if (invoker == null) {
            return FilterResult.success();
        }
        return invoker.invoke(filterContext);
    }
}
