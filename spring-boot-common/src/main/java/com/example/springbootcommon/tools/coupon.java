package com.example.springbootcommon.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class coupon {

    public static void main(String[] args) {
        List<List<CouponInfo>> dim = new ArrayList<>();

        List<CouponInfo> o1 = new ArrayList<>();
        o1.add(new CouponInfo("a"));
        o1.add(new CouponInfo("b"));
        o1.add(new CouponInfo("c"));
        dim.add(o1);

        List<CouponInfo> o2 = new ArrayList<>();
        o2.add(new CouponInfo("b"));
        o2.add(new CouponInfo("c"));
        o2.add(new CouponInfo("d"));
        dim.add(o2);

        List<CouponInfo> o3 = new ArrayList<>();
        o3.add(new CouponInfo("c"));
        o3.add(new CouponInfo("d"));
        o3.add(new CouponInfo("e"));
        dim.add(o3);

        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(descartes(dim)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static List<CombinationCoupons> descartes(List<List<CouponInfo>> dimValue) {
        List<CombinationCoupons> couponCombinations = new ArrayList<>();
        List<List<CouponInfo>> result = new ArrayList<>();
        if (dimValue == null || dimValue.size() == 0)
            return couponCombinations;
        backtrace(dimValue, 0, result, new ArrayList<>());
        for(List<CouponInfo> couponInfos:result){
            CombinationCoupons combination = new CombinationCoupons();
            combination.setCouponInfos(couponInfos);
            couponCombinations.add(combination);
        }
        return couponCombinations;

    }

    /**
     * 递归回溯法求解
     *
     * @param dimValue 原始数据集合
     * @param index 当前执行的集合索引
     * @param result 结果集合
     * @param curList 当前的单个结果集
     */
    private static void backtrace(List<List<CouponInfo>> dimValue, int index,
                           List<List<CouponInfo>> result, List<CouponInfo> curList) {
        if (curList.size() == dimValue.size())
            result.add(new ArrayList<>(curList));
        else
            for (int j = 0; j < dimValue.get(index).size(); j++) {
                curList.add(dimValue.get(index).get(j));
                backtrace(dimValue, index + 1, result, curList);
                curList.remove(curList.size() - 1);
            }
    }

    @Data
    public static class CouponInfo {

        public CouponInfo(String name){
            this.name = name;
        }
        public String name;
    }

    @Data
    public static class CombinationCoupons {

        List<CouponInfo> couponInfos;
    }
}

