package com.example.springbootcommon.java8.collection;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

public class ListTest {

    public static void main(String[] args) {
        Random rand =new Random(10000);
//        Map<String, String> map = new HashMap<>(100);
//        for (int i = 0; i <100; i++) {
//            map.put(String.valueOf(rand.nextInt()), String.valueOf(rand.nextInt()));
//        }

        List<User> list = new ArrayList<>(100);
        for (int i = 0; i <100; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName("name" + rand.nextInt());
            list.add(user);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Map map = list.stream().collect(Collectors.toMap(User::getUserId, User::getUserName));
        }
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start);

        for (int i = 0; i < 100000; i++) {
            Map map = new HashMap(100);
            for (User user : list) {
                map.put(user.getUserId(), user.getUserName());
            }
        }
        System.out.println(System.currentTimeMillis() - end1);
    }

    @Data
    static
    class User{
        String userName;
        Integer userId;
    }
}
