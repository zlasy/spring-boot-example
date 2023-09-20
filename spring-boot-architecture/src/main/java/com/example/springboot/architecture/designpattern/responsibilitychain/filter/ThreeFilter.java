package com.example.springboot.architecture.designpattern.responsibilitychain.filter;

import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.InvokeResult;
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
    public InvokeResult doFilter(Invoker<?> invoker, AuditFilterContext filterContext) {
        System.out.println("filter3: " + filterContext.getDeliverInfo().getDeliverCode());
        if (invoker == null) {
            return InvokeResult.success();
        }
        return invoker.invoke(filterContext);
    }
}
