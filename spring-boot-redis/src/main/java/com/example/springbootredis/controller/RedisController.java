package com.example.springbootredis.controller;

import com.example.springbootredis.vo.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class RedisController {

    @RequestMapping("/getStr/{key}")
    public String getStr(@PathVariable String key) {
        return key;
    }

    @RequestMapping("/getUser")
    @Cacheable(cacheNames="user")
    public User getUser() {
        User user=new User("zhangsan", 456L, 18, "aa123");
        System.out.println("无缓存调用");
        return user;
    }

    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
}
