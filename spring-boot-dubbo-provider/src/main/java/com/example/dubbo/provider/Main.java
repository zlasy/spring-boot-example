package com.example.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.dubbo.provider"})
@EnableDubbo
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
