package com.example.springboot.architecture.designpattern.responsibilitychain.filter;

import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.InvokeResult;

public interface AuditFilter {

    public InvokeResult doFilter(Invoker<?> invoker, AuditFilterContext filterContext);
}
