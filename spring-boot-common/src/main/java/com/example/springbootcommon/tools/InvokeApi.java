package com.example.springbootcommon.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import lombok.Data;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class InvokeApi {

    public static void main(String[] args) throws UnsupportedEncodingException {
        callFromFile();
//        getFile();
    }

    public static void printBytes() {

        List<Integer> roomMembers = Arrays.asList(4484507,4484516,4484500,4484522,4484531,4484515,4487289,4484508,4484523,4486679,4484524,4484509,4484510,4486711,4486699,4484518,4484532,4486680,4484541,4466197,4468304,4468312,4468313,4468305,4468288,4468287,4468306,4468314,4468289,4468307,4468290,4468321,4468294,4468295,4468322,4468296,4468323,4468297,4468316,4698005,4698013,4697989,4698006,4697997,9109952,9109951,9109939,9109928,9109927,9109940,9109938,9109962,9109961,9109926,6120970,6161695,6163540,9113688,9379135,9113670,9381505,9113678,9378884,9384349,9113658,9113692,9380865,9113691,9113677,9113679,9113693,9113659,9365642,9365618,9385996,6120466,9365647,9365662,9365643,9365671,9365620,9365652,9365645,9365646,9365619,9365635,9365628,9365637,9365630,9365639,9365649,9365636,9365661,9365626,9365638,9365629,9365650,9365640,9365651,9365627,9365648,9365672,9365681,9365663,9365653,9365654,9365673);
        Map<Integer, List<Integer>> roomGroups = new HashMap<>(32);
        roomMembers.forEach(m -> {
            Integer db = (int)(m % 32);
            if (!roomGroups.containsKey(db)){
                roomGroups.put(db, new ArrayList<>());
            }
            roomGroups.get(db).add(m);
        });

//        Map m = roomMembers.stream().collect(Collectors.groupingBy(m -> {
//            return (int) (m % 32);
//        }));

//        System.out.println(roomMembers.stream().map(Long::intValue).collect(Collectors.toList()));
        for (int i = 0; i < 32; i++) {
            StringJoiner sj = new StringJoiner(",");
            roomGroups.get(i).forEach(x -> sj.add(Integer.toString(x)));
            System.out.println("delete from  IM_message" + i + ".message where tenant_id = 1 and room_id in ( " + sj.toString() + " )");
        }
    }

    public static void getFile(){
        String url = "";
        String filename = "/Users/zhangle/Documents/1.txt";
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8);
            BufferedReader br=new BufferedReader(in);
            String temp=br.readLine();
            while(temp!=null){
                getMethod(url, temp);
                temp=br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getMethod() {
        final String URL = "";
        final String ids = "11053172,11053173,11053175,11053176,11058432,11058747,11058748,11058750,11058752,11058753,11058754,11061651,11065768,11067895,11067899,11068849,11068957,11068958,11068959,11068960,11068961,11068962,11069528,11070161,11081081,11081830,11084415,11084417,11099504,11099505,11099506,11099538,11099539,11099540,11099541,11099542,11099844,11099850,11099851,11099852,11099857,11099863,11099864,11100634,11100986,11100987,11102835,11102993,11102996,11104146,11104652,11104692,11105228,11105230,11105538,11106489,11111232,11111502,11112729,11112735,11112893,11112894,11112895,11112896,11112897,11113590,11113848,11114426,11550634";
        try{
            String[] idList = ids.split(",");
            for (String s : idList) {
                getMethod(URL, s);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void getMethod(String URL, String s) {
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(URL + s);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        try {
            client.executeMethod(method);
            System.out.println(s);
            System.out.println(method.getResponseBodyAsString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
    }

    public static void postMethod() {
        final String URL = "";
        final String ids = "RE20221226104000621,RE20221226104000622,RE20221226104000625,RE20221226104000626,RE20221226104000627,RE20221226104000632,RE20221226104000634,RE20221226104000635,RE20221226104000637,RE20221226104000640,RE20221226104000646,RE20221226104000647,RE20221226104000650,RE20221226104000652,RE20221226104000653,RE20221226104000656,RE20221226104000660,RE20221226104000661,RE20221226104000662,RE20221226104000665,RE20221226104000668,RE20221226104000669,RE20221226104000670,RE20221226104000673,RE20221226104000675,RE20221226104000676,RE20221226104000677,RE20221226104000679,RE20221226104000680,RE20221226104000681,RE20221226104000687,RE20221226104000689,RE20221226104000691,RE20221226104000692,RE20221226104000694,RE20221226104000695,RE20221226104000697,RE20221226104000698,RE20221226104000699,RE20221226104000700,RE20221226104000702,RE20221226104000703,RE20221226104000704,RE20221226104000706,RE20221226104000709";
        try {
            String[] idList = ids.split(",");
            for (String s : idList) {
                QueryOutOrderParam param = new QueryOutOrderParam();
                param.setSchoolId(104);
                param.setPurchaseCode(s);
                param.setDeliverCompanyType(1);
                param.setWarehouseId(910);

                HttpClient client = new HttpClient();
                PostMethod method = new PostMethod(URL);
                RequestEntity se = new StringRequestEntity(JSON.toJSONString(param) ,"application/json" ,"UTF-8");
                method.setRequestEntity(se);
                method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
                client.executeMethod(method);
                System.out.println(s);
                System.out.println(method.getResponseBodyAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void callFromFile(){

        String url = "";
        String filename = "/Users/zhangle/Documents/1.txt";
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8);
            BufferedReader br=new BufferedReader(in);
            String temp=br.readLine();
            while(temp!=null){
                HttpClient client = new HttpClient();
                PostMethod method = new PostMethod(url);
                RequestEntity se = new StringRequestEntity(temp ,"application/json" ,"UTF-8");
                method.setRequestEntity(se);
                method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
                client.executeMethod(method);
//                System.out.println(temp);
                System.out.println(temp + "\t" + method.getResponseBodyAsString());

                temp=br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Data
    public static class QueryOutOrderParam {
        Integer schoolId;
        String purchaseCode;
        Integer deliverCompanyType;
        Integer warehouseId;
    }
}
