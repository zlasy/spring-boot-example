package com.example.springboot.architecture.test;

import com.example.springboot.architecture.SpringBootArchitectureApplication;
import com.example.springboot.architecture.designpattern.responsibilitychain.ApplicationContextHolder;
import com.example.springboot.architecture.designpattern.responsibilitychain.FilterChainBuilder;
import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import com.example.springboot.architecture.designpattern.responsibilitychain.filter.AuditFilterContext;
import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.DeliverInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootArchitectureApplication.class)
public class BaseTest {


    @Test
    public void query(){
        Map<String, FilterChainBuilder> map = ApplicationContextHolder.getBeansOfType(FilterChainBuilder.class);
        for (Map.Entry<String, FilterChainBuilder> entry : map.entrySet()) {
            DeliverInfo d1 = DeliverInfo.mock1();
            Invoker<Object> invoker = entry.getValue().buildFilterChain();
            if (invoker != null) {
                AuditFilterContext filterContext = new AuditFilterContext();
                filterContext.setDeliverInfo(d1);
                invoker.invoke(filterContext);
            }
        }
    }

}