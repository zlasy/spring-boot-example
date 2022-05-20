package com.example.springboot.architecture.designpattern.responsibilitychain;

import com.example.springboot.architecture.designpattern.responsibilitychain.filter.AuditFilterContext;
import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.FilterResult;

public interface Invoker<T> {

    FilterResult invoke(AuditFilterContext filterContext);
}
