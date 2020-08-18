package com.example.springbootcommon.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaTest {

    public static void main(String[] args) {

        System.out.println(ClassB.A);

        List<String> list = Arrays.asList("hello", "", "HELLO", "Hello", "World", "World", " ");
        list.add("");
        Object count = list.stream()
                .map(String::trim)
                .filter(String::isEmpty)
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .toArray()[0];

        System.out.println(count);
    }
}
