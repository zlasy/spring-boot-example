package com.example.springboot.architecture.designpattern.responsibilitychain.filter;

import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.FilterResult;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilterChainNode<T> implements Invoker<T> {

    AuditFilter filter;
    Invoker<T> nextNode;

    @Override
    public FilterResult invoke(AuditFilterContext filterContext) {
        return filter.doFilter(nextNode, filterContext);
    }
}
