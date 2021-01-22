package com.example.springbootcommon.java8;

import java.math.BigDecimal;

public class Random {

    private static final java.util.Random RANDOM = new java.util.Random();

    public static void main(String[] args) {
        for (int i = 0 ; i < 100 ; i++){
            System.out.println(new BigDecimal(nextInt(0, 100)));
        }
    }

    public static int nextInt(int startInclusive, int endExclusive) {
        return startInclusive == endExclusive ? startInclusive : startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
    }
}

