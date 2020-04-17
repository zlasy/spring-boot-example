package com.example.dubbo.provider.controller;

import com.example.dubbo.result.ApiResult;
import com.example.dubbo.result.dto.UserDTO;
import com.example.dubbo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoRestController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/test/{str}")
    public ApiResult<?> getTestString(@PathVariable String str){
        return ApiResult.builder().code("0").success(true).message("success")
                .data(demoService.test(str)).build();
    }


    @PostMapping("/test/createUser")
    public ApiResult<?> getTestString(@RequestBody @Validated UserDTO user){
        return ApiResult.builder().code("0").success(true).message("success")
                .data(user).build();
    }
}
