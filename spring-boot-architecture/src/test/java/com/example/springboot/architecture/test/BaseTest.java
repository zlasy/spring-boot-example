package com.example.springboot.architecture.test;

import com.example.springboot.architecture.SpringBootArchitectureApplication;
import com.example.springboot.architecture.designpattern.responsibilitychain.ApplicationContextHolder;
import com.example.springboot.architecture.designpattern.responsibilitychain.FilterChainBuilder;
import com.example.springboot.architecture.designpattern.responsibilitychain.Invoker;
import com.example.springboot.architecture.designpattern.responsibilitychain.extension.ExtensionLoader;
import com.example.springboot.architecture.designpattern.responsibilitychain.factory.FilterChainBuilderFactory;
import com.example.springboot.architecture.designpattern.responsibilitychain.filter.AuditFilter;
import com.example.springboot.architecture.designpattern.responsibilitychain.filter.AuditFilterContext;
import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.DeliverInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootArchitectureApplication.class)
public class BaseTest {

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000));

    @Resource
    FilterChainBuilderFactory filterChainBuilderFactory;

    @Test
    public void query(){
        FilterChainBuilder builder = filterChainBuilderFactory.getFilterChainBuilder("special");
        Invoker<Object> invoker = builder.buildFilterChain();
        if (invoker != null) {
            for (int i = 1; i <= 3; i++) {
                int finalI = i;
                threadPoolExecutor.submit(() -> {
                    AuditFilterContext filterContext = new AuditFilterContext();
                    filterContext.setDeliverInfo(DeliverInfo.mock(finalI));
                    invoker.invoke(filterContext);
                });
            }
        }
    }

    @Test
    public void test1(){
        List<AuditFilter> filters = ExtensionLoader.getExtensionLoader(AuditFilter.class).getFilterExtension();
        System.out.println(filters);
    }

}