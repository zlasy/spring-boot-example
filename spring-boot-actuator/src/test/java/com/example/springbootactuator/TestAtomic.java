package com.example.springbootactuator;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomic {

    static final  AtomicInteger a = new AtomicInteger();
    public static void main(String[] args) {

        for (int i=0; i<10;i++){
            System.out.println(a.incrementAndGet());
        }
    }
}
