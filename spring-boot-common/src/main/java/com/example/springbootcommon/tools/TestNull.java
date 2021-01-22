package com.example.springbootcommon.tools;

import java.util.Optional;

public class TestNull {

    public final Integer i;

    TestNull(){
        i = 10;
    }

    public static void main(String[] args) {
        try {
            float f = 0.00f;
            System.out.println(f == 0.0f);
            Integer a = -3;
            Optional<Integer> t = Optional.ofNullable(a);
            t.ifPresent(x -> System.out.println(x.equals(1)));
            System.out.println(Optional.ofNullable(a).orElse(1) > 0);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
