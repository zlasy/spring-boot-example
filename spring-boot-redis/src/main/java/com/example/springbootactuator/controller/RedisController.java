package com.example.springbootactuator.controller;

import com.example.springbootactuator.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/getStr/{key}")
    public String getStr(@PathVariable String key) {
        return key;
    }

    @RequestMapping("/getUser")
    @Cacheable(cacheNames="user")
    public User getUser(String name) {
        User user=new User(name, 456L, 18, "aa123");
        System.out.println("无缓存调用");
        return user;
    }

    @GetMapping("/uid")
    String uid(HttpSession session, String id) {
        System.out.println("uid=" + id);
        UUID uid = (UUID) session.getAttribute(id);
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute(id, uid);
        return session.getId();
    }

    @RequestMapping("userScan")
    String userScan(){
        Set<String> keys = scan("userIdMapperConnIds:1:");
        for (String key : keys) {
//            redisTemplate.delete(key);
            Set<Long> valueSet = redisTemplate.opsForSet().members(key);
            boolean hasValue = false;
            for (Long value : valueSet) {
                if (redisTemplate.hasKey("connectionId:1:" + value)){
                    System.out.println("存在" + key + "|" + value);
                    hasValue = true;
                } else {
                    System.out.println("不存在" + key + "|" + value);
                }
            }
            if (!hasValue){
                redisTemplate.delete(key);
            }
        }
        return "ok";
    }

    @RequestMapping("roomScan")
    String scan(){
        Set<String> keys = scan("roomIdMapperSessionId:");
        for (String key : keys) {
            String value =  Long.toString((Long) redisTemplate.opsForValue().get(key));
            if (redisTemplate.hasKey("redisSessionInfo:1:" + value)){
                System.out.println("存在" + key + "|" + value);
            } else {
                System.out.println("不存在" + key + "|" + value);
                redisTemplate.delete(key);
            }
//            System.out.println("delete " + key);
        }
        return "ok";
    }

    public Set<String> scan(String matchKey) {
        Set<String> keys = (Set<String>) redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keysTmp = new HashSet<>();
            Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder().match(matchKey + "*").count(100).build());
            while (cursor.hasNext()) {
                keysTmp.add(new String(cursor.next()));
            }
            return keysTmp;
        });
        return keys;
    }
}
