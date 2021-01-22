package com.example.springbootcommon.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Animal {
    static Integer cardNum = 5;
    static List<int[]> cards = new ArrayList<>(cardNum);
    static int cnt = 0;
    public static void main(String[] args) {
        for (int i = 0; i< cardNum; i++){
            cards.add(new int[]{0,1,2,3,4,5});
        }
        init(0);
    }

    private static void init(Integer num) {
        if (!matchNum(num)){
            return;
        }
        if (num.equals(cardNum)) {
            System.out.println(++cnt);
            if (match(cards)){
                print(cards);
            }
            return;
        }
        int[] card = cards.get(num);
        for (int a0=0;a0<=12;a0++){
            card[0] = a0;
            for (int a1=a0+1;a1<=13;a1++){
                card[1] = a1;
                for (int a2=a1+1;a2<=14;a2++){
                    card[2] = a2;
                    for (int a3=a2+1;a3<=15;a3++){
                        card[3] = a3;
                        for (int a4=a3+1;a4<=16;a4++){
                            card[4] = a4;
                            for (int a5=a4+1;a5<=17;a5++){
                                card[5] = a5;
                                init(num + 1);
                            }
                        }
                    }
                }
            }
        }
        init(num + 1);
    }

    private static boolean matchNum(Integer num) {
        if (num <= 1 ) return true;
        for (int i = 0; i<num-1; i++){
            for (int j = i+1; j<num; j++){
//                System.out.println(num + "|" + i + "," + j);
                if (!onlyOneMatch(cards.get(i), cards.get(j))){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean match(List<int[]> cards) {
        for (int a = 0; a < cardNum -1; a++) {
            int[] carda = cards.get(a);
            for(int b = a+1; b<cardNum; b++){
                int[] cardb = cards.get(b);
//                System.out.println(a + "," + b);
                if (!onlyOneMatch(carda, cardb)){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean onlyOneMatch(int[] carda, int[] cardb) {
//        System.out.println(Arrays.asList(carda));
//        System.out.println(Arrays.asList(cardb));
        int count = 0;
        for (int i = 0; i<6; i++){
            Integer a = carda[i];
            for (int j = 0; j<6; j++){
                Integer b = cardb[j];
                if (a.equals(b)){
                    count++;
                    if (count > 1){
                        return false;
                    }
                }
            }
        }
        return count == 1;
    }

    private static void print(List<int[]> cards) {
        System.out.println("------");
        for (int a = 0; a < cardNum; a++) {
            int[] card = cards.get(a);
            StringBuilder text = new StringBuilder();
            for (int i = 0 ; i < 6; i++){
                text.append(card[i]).append(",");
            }
            System.out.println(a + "|" + text.toString());
        }
    }
}
