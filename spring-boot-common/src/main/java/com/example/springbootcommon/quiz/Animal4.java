package com.example.springbootcommon.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Animal4 {
    static Integer cardNum = 5;
    static List<int[]> cards = new ArrayList<>(cardNum);
    static List<int[]> pool = new ArrayList<>(20000);
    static int cnt = 0;


    public static void main(String[] args) {
        initPool();
        for (int i = 0; i < cardNum; i++){
            cards.add(new int[6]);
        }
        System.out.println(pool.size());
        find(0);
    }

    private static void find(int num) {
        if (num == cardNum) {
            print();
            return;
        }
        for (int i = 0; i < pool.size(); i++){
            int[] temp = pool.get(i);
            if (!match(temp, num)){
                continue;
            }
            cards.set(num, pool.get(i));
            find(num + 1);
        }
    }

    private static boolean match(int[] temp, int num) {
        int[] flag = new int[18];
        for (int c = 0; c < 6; c++){
            flag[temp[c]] = 1;
        }
        for (int i = 0; i < num; i++){
            if (!onlyOneMatch(flag, cards.get(i))){
                return false;
            }
        }
        return true;
    }

    private static void initPool() {
        for (int a0 = 0; a0 <= 12; a0++) {
            for (int a1 = a0 + 1; a1 <= 13; a1++) {
                for (int a2 = a1 + 1; a2 <= 14; a2++) {
                    for (int a3 = a2 + 1; a3 <= 15; a3++) {
                        for (int a4 = a3 + 1; a4 <= 16; a4++) {
                            for (int a5 = a4 + 1; a5 <= 17; a5++) {
                                int[] a = new int[]{a0, a1, a2, a3, a4, a5};
                                pool.add(a);
                            }
                        }
                    }
                }
            }
        }

    }

    private static boolean onlyOneMatch(int[] flag, int[] cardb) {
//        System.out.println(Arrays.asList(carda));
//        System.out.println(Arrays.asList(cardb));
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (flag[cardb[i]] == 1){
                count++;
                if (count > 1){
                    return false;
                }
            }
        }
        return count == 1;
    }

    private static void print() {
        System.out.println("-----------");
        for (int i = 0; i<cardNum; i++){
            int[] card = cards.get(i);
            StringBuilder text = new StringBuilder();
            for (int a = 0 ; a < 6; a++){
                text.append(card[a]).append(",");
            }
            System.out.println(i + "|" + text.toString());
        }
    }
}