package com.example.springbootcommon.tools;

import java.util.Optional;

public class TestNull {

    public final Integer i;

    TestNull(){
        i = 10;
    }

    public static void main(String[] args) {
        try {
            Integer a = -3;
            Optional<Integer> t = Optional.ofNullable(a);
            t.ifPresent(x -> System.out.println(x.equals(1)));
            System.out.println(Optional.ofNullable(a).orElse(1) > 0);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
