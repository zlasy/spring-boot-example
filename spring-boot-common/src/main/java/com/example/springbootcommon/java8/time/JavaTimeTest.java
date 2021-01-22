package com.example.springbootcommon.java8.time;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JavaTimeTest {

    public static void main(String[] args) {
        int expire = 360;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println(calendar);
        calendar.add(Calendar.DATE, expire);
        System.out.println(calendar);


        String apiUrl = "asdfasdf%sx1111";
        List<Integer> productIds = Arrays.asList(9001, 101);
        String uri = String.format(apiUrl, productIds.stream().map(Object::toString).collect(Collectors.joining(",")));
        System.out.println(uri);
    }
}
