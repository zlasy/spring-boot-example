package com.example.dubbo.consumer.controller;

import com.example.dubbo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DubboConsumerController {

//    @Reference(version = "1.0.0", url = "dubbo://localhost:8099")
    @Reference(registry = "r1")
    private DemoService demoService;



    @RequestMapping(path="/rest/{key}", method = RequestMethod.GET)
    public String getStr(@PathVariable String key) {
        return demoService.test(key);
    }
}