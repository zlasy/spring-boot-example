package com.example.springboot.architecture.designpattern.responsibilitychain;

import com.example.springboot.architecture.designpattern.responsibilitychain.filter.AuditFilter;
import com.example.springboot.architecture.designpattern.responsibilitychain.filter.AuditFilterContext;
import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.InvokeResult;
import lombok.AllArgsConstructor;
import lombok.Data;

public interface FilterChainBuilder {

    <T> Invoker<T> buildFilterChain();

    @Data
    @AllArgsConstructor
    public class InvokeChainNode<T> implements Invoker<T> {

        AuditFilter filter;
        Invoker<T> nextNode;

        @Override
        public InvokeResult invoke(AuditFilterContext filterContext) {
            return filter.doFilter(nextNode, filterContext);
        }
    }
}
