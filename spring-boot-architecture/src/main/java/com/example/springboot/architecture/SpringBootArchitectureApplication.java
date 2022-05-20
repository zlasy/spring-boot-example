package com.example.springboot.architecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootArchitectureApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootArchitectureApplication.class, args);
    }

}
