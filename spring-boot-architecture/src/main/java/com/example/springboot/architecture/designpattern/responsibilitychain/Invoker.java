package com.example.springboot.architecture.designpattern.responsibilitychain;

import com.example.springboot.architecture.designpattern.responsibilitychain.filter.AuditFilterContext;
import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.InvokeResult;

public interface Invoker<T> {

    InvokeResult invoke(AuditFilterContext filterContext);
}
