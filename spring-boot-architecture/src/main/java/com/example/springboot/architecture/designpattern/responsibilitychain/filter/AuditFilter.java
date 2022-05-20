package com.example.springboot.architecture.designpattern.responsibilitychain.filter;

import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.FilterResult;

public interface AuditFilter {

    public FilterResult doFilter(Invoker<?> invoker, AuditFilterContext filterContext);
}
