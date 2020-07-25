package com.example.springbootactuator.controller;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ActuatorController {

    private static final AtomicInteger COUNTER = new AtomicInteger();

    @Resource
    MeterRegistry registry;

    @GetMapping("test")
    public String counter(){
        try {
            Gauge.builder("request.rest.conn",
                    this, value -> COUNTER.getAndIncrement())
                    .tag("path", "counter")
                    .register(registry);
            return COUNTER.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "fail";
    }
}
