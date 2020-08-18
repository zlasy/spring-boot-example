package com.example.springbootcommon.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ModSequence {

    public static void main(String[] args) {
        System.out.println("please input");
        Scanner scan = new Scanner(System.in);
        String[] first = scan.nextLine().split(" ");
        String[] second = scan.nextLine().split(" ");
        long d = Long.parseLong(first[0]);
        List<Long> n = Arrays.stream(second).map(Long::parseLong).collect(Collectors.toList());
//        List<Integer> n = Arrays.asList(1,9223372036854775806,7);

        System.out.println(count(n, d,0));
    }

    private static int count(List<Long> n, long d, int i) {
        int sum = 0;
        long temp = 0;
        for (int i1 = i; i1 < n.size(); i1++) {
            temp += n.get(i1) % d;
            if (temp % d == 0){
                sum++;
            }
        }

        if (i == n.size() - 1) return sum;
        else return sum + count(n, d, ++i);
    }
}
