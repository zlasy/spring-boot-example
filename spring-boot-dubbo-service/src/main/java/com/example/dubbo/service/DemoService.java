package com.example.dubbo.service;

import com.example.dubbo.result.dto.UserDTO;

public interface DemoService {

    String test(String str);

    String createUser(UserDTO user);
}
