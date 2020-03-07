package com.example.springbootcommon.java8;


import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.Comparator;

public class FunctionalInterfaceTest {
    public static void main(String[] args) {
        Integer[] array = {1, 4, 3, 2, 6, 9, 8};
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 1000; i++) {
            Arrays.sort(array, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }
            });                                             // 倒序排序
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

        stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 1000; i++) {
            Arrays.sort(array, (v1, v2) -> v1 - v2);        // 正序排序
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}