package com.example.springboot.architecture.designpattern.responsibilitychain.filter;

import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import com.example.springboot.architecture.designpattern.responsibilitychain.annotation.SpecialDeliverFilter;
import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.InvokeResult;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@SpecialDeliverFilter
@Component
public class TwoFilter implements AuditFilter{
    @Override
    public String toString(){
        return "2";
    }

    @Override
    public InvokeResult doFilter(Invoker<?> invoker, AuditFilterContext filterContext) {
        System.out.println("filter2: " + filterContext.getDeliverInfo().getDeliverCode());
        return invoker.invoke(filterContext);
    }
}
