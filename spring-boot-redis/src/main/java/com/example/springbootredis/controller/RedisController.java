package com.example.springbootredis.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @RequestMapping("/getStr/{key}")
    public String getStr(@PathVariable String key) {
        return key;
    }

}
