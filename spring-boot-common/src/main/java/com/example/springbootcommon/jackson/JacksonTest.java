package com.example.springbootcommon.jackson;

import java.util.Date;

public class JacksonTest {

    public static void main(String[] args) {
        JsonUser user = new JsonUser();
        user.setUserId(123L);
        user.setUserName("userTest");
        user.setSex(JsonUser.SEX.MALE);
        user.setCreationDate(new Date());

        String json = JacksonUtil.bean2Json(user);
        System.out.println(json);
        JsonUser user1 = JacksonUtil.json2Bean(json, JsonUser.class);
        System.out.println(user1);

    }
}
