package com.example.springboot.architecture.designpattern.responsibilitychain.filter;

import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import com.example.springboot.architecture.designpattern.responsibilitychain.annotation.SpecialDeliverFilter;
import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.InvokeResult;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(99999)
@SpecialDeliverFilter
@Component
public class EndFilter implements AuditFilter{
    @Override
    public String toString(){
        return "end";
    }

    @Override
    public InvokeResult doFilter(Invoker<?> invoker, AuditFilterContext filterContext) {
        System.out.println("filter-end: " + filterContext.getDeliverInfo().getDeliverCode());
        if (invoker == null) {
            return InvokeResult.success();
        }
        return invoker.invoke(filterContext);
    }
}
