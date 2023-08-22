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
//        final String URL = "http://jassetapi.staff.xdf.cn/asset/fixUseUser?id=";
        final String URL = "http://172.25.2.184/deliverTimeInitialHandler?eventType=0&id=";
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
//        method.addRequestHeader("cookie", "XDFUUID=723879c0-7479-70c6-04e7-10f09ac240f1; gr_user_id=04359ee4-647b-4639-969e-ef90a0b7ccd6; 91c8d7964b724d0a_gr_last_sent_cs1=f5c97b2d8a463135818d7f2d00c000d8; mainOrgId=2000; LOGIN_ORG_ID=3; LOGIN_DEPT_ID=4; XDF_WB_TOKEN=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNTI2NTgxOCIsImV4cCI6MTY4NDk4MDEwNCwiaWF0IjoxNjg0ODkzNzA0fQ.kGJYeFRRUU89jRXoHfW0fc0SUD-DMuM3TrIJGMns3lr3MeCWnUix_YRLpmSDx1gywz9eHpYfHQFJb9BwDSWWfw; e2e=B3D0F9A447CB6D960EEB22F0C56E27F9; OA_USER_ID=zhangle24; XDF_H5_TOKEN=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTA3NTA3OCIsImV4cCI6MTY4Njc5Njk0OSwiaWF0IjoxNjg1NTg3MzQ5fQ.EenCXllsOiB9LY9r0YkvhaKIJ6PRtK3o-_55XA6s8KsynXKRkG81HIsSsrYrbMo2cTjnMb9p7olX_3wGuSd_kA; e2mf=991b94c0cacc403293847b6b97cdece9; xone_e2e=B3D0F9A447CB6D960EEB22F0C56E27F9; xone=991b94c0cacc403293847b6b97cdece9; casgwusercred=PSo80dj5aPS6GpMvFP_AaY7fQ4KoVqlag9rTz4vNuGgstyBWkJ5vWek6Mk1UBi9-RopQzcgYsaKMGHE7V011OQbb4f92b8bf28122eb366dbf0e8ad4cdc; 91c8d7964b724d0a_gr_cs1=f5c97b2d8a463135818d7f2d00c000d8");
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
        final String URL = "http://172.25.2.170/out/cancelPurchaseOrder";
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
//                method.addRequestHeader("cookie", "SESSION=YWI5N2JkNzQtMzBlYy00NTk2LTk3NzAtNmYxZjBlZjBjODI1; uniqueVisitorId=0e15f49b-033b-50bc-87ec-ba4b343dc134; gr_user_id=e812e703-6479-4374-93f0-a6321d8b4146; 91c8d7964b724d0a_gr_last_sent_cs1=f5c97b2d8a463135818d7f2d00c000d8; _ga=GA1.2.1334156812.1663673229; Admin-Token=eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6ImNjMmIzNGQ5LTIwY2QtNGZiOC1iMzU5LWU4M2VjYmU3ZGY1YyJ9.0M4jR8QD3XSoHE0Sl0Yx2tcm07ID6zGBFuDpZ8VB6YqrP-hrQKhx8M9GRdY0EFnJIKXnzmIKHW5cV8JziZo-uQ; KMSS_Style=default; JSESSIONID=00048NHdhFtP2GYO7CFjav2vZkD:-45HBJP:-3397PL:1ACNTUVSSA; e2e=B3D0F9A447CB6D960EEB22F0C56E27F9; OA_USER_ID=zhangle24; LOGIN_ORG_ID=3; LOGIN_DEPT_ID=4; XDFUUID=765a90f8-1f50-5ead-c3bf-ebaab74db3a1; Hm_lvt_e010d1faf316a4dbfe8639481a2a3f90=1668503481; Fingerprint_xdf=1668503486968_0.5848535021719454; soukecityid=1; cityCode=110100; cityName=%E5%8C%97%E4%BA%AC; Hm_lpvt_e010d1faf316a4dbfe8639481a2a3f90=1668765883; __xsptplus342=342.5.1668765883.1668765883.1%234%7C%7C%7C%7C%7C%23%23rSuF3ICCMUY04ZDt7eLrOsgltgIA-Orn%23; IXUE_NAME=%E5%BC%A0%E4%B9%90; XDF_WB_TOKEN=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNTI2NTgxOCIsImV4cCI6MTY3MDU1MDkxMSwiaWF0IjoxNjcwNDY0NTExfQ.kVoopKLZHflerpemOtwesmV14k6Jr_0vOQIZ69oQj2Hnw7Y1ezqzmuGp5l_KvnARxGI6rXRw-Xwas-e-WlO2uw; e2mf=6547cb11874742649b40a78898e73d83; rem=on; casgwusercred=Mx47IHy5maDxjyUP7UiZmB9ZoN5iRRMuOrq8hsm-e-gMC69ARyW2X2XslZW4MlDfjNofNvZadCn7BzCJLZhOogd95b9a2379e86f292aa116ac13e159aa; 91c8d7964b724d0a_gr_cs1=f5c97b2d8a463135818d7f2d00c000d8; 964de61476ecd75d_gr_session_id=67f0cb21-db83-4b68-8d72-99ced0ccd846; 964de61476ecd75d_gr_session_id_67f0cb21-db83-4b68-8d72-99ced0ccd846=true");
                client.executeMethod(method);
                System.out.println(s);
                System.out.println(method.getResponseBodyAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void callFromFile(){
//        String url = "http://172.25.2.184/exchangeOutSnHandler";
//        String url = "http://172.25.2.183/addReverseExchangeOrderV2";
//        String url = "http://172.25.2.201/stock/freeOccupy";
//        String url = "http://172.25.2.170/out/queryGoodsStockBatch";
//        String url = "http://172.25.2.169/rq/sendMessage";
        String url = "http://jassetapitool.staff.xdf.cn:8080/asset/tools/update";
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
                method.addRequestHeader("cookie", "rem=on; gr_user_id=c4b66ec4-e30c-449a-ab59-40fd7a177428; e2e=B3D0F9A447CB6D960EEB22F0C56E27F9; e2mf=9d3e65fc793f47cfb8f9315e340e3f95; arkSession=70993599-d2df-41cd-bfdf-42200eef7e6c; casgwusercred=TBpk5U72jm2WFSm1MNBQvZsWS1ZwfIthuJY1aIIU4mCfkqpIq7sU2aEEA9QMcuWKs707wWbDqxXI7nb966lV6A7d33ecdfbcc1ce819f6a501f28a44dda");
                client.executeMethod(method);
//                System.out.println(temp);
                System.out.println(temp + "\t" + method.getResponseBodyAsString());

                temp=br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendIxueSms(){
        String url = "https://ixue.xdf.cn/ixue-backend/api/advertise/sendMessage";
        String filename = "/Users/zhangle/Documents/1.txt";
        try {
            List<String> emailList = new ArrayList<>(500);
            InputStreamReader in = new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8);
            BufferedReader br=new BufferedReader(in);
            String temp=br.readLine();
            int page = 0;
            while(temp!=null){
                emailList.add(temp);
                if (emailList.size() == 500) {
                    callSendSmsApi(url, emailList);
                    System.out.println(++page);
                }
                temp=br.readLine();
            }
            callSendSmsApi(url, emailList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void callSendSmsApi(String url, List<String> emailList) throws IOException {
        String param = JSON.toJSONString(ImmutableMap.of("emailList", emailList));
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        RequestEntity se = new StringRequestEntity(param,"application/json" ,"UTF-8");
        method.setRequestEntity(se);
        method.addRequestHeader("cookie", "SESSION=YmFlN2E2YTktM2I1NC00MjRjLWFjMjEtZTZjZWE4ZTBmMTA1; uniqueVisitorId=0e15f49b-033b-50bc-87ec-ba4b343dc134; gr_user_id=e812e703-6479-4374-93f0-a6321d8b4146; 91c8d7964b724d0a_gr_last_sent_cs1=f5c97b2d8a463135818d7f2d00c000d8; _ga=GA1.2.1334156812.1663673229; Admin-Token=eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6ImNjMmIzNGQ5LTIwY2QtNGZiOC1iMzU5LWU4M2VjYmU3ZGY1YyJ9.0M4jR8QD3XSoHE0Sl0Yx2tcm07ID6zGBFuDpZ8VB6YqrP-hrQKhx8M9GRdY0EFnJIKXnzmIKHW5cV8JziZo-uQ; KMSS_Style=default; JSESSIONID=00048NHdhFtP2GYO7CFjav2vZkD:-45HBJP:-3397PL:1ACNTUVSSA; e2e=B3D0F9A447CB6D960EEB22F0C56E27F9; OA_USER_ID=zhangle24; LOGIN_ORG_ID=3; LOGIN_DEPT_ID=4; XDFUUID=765a90f8-1f50-5ead-c3bf-ebaab74db3a1; Hm_lvt_e010d1faf316a4dbfe8639481a2a3f90=1668503481; Fingerprint_xdf=1668503486968_0.5848535021719454; soukecityid=1; cityCode=110100; cityName=%E5%8C%97%E4%BA%AC; Hm_lpvt_e010d1faf316a4dbfe8639481a2a3f90=1668765883; __xsptplus342=342.5.1668765883.1668765883.1%234%7C%7C%7C%7C%7C%23%23rSuF3ICCMUY04ZDt7eLrOsgltgIA-Orn%23; IXUE_NAME=%E5%BC%A0%E4%B9%90; XDF_WB_TOKEN=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNTI2NTgxOCIsImV4cCI6MTY3MDU1MDkxMSwiaWF0IjoxNjcwNDY0NTExfQ.kVoopKLZHflerpemOtwesmV14k6Jr_0vOQIZ69oQj2Hnw7Y1ezqzmuGp5l_KvnARxGI6rXRw-Xwas-e-WlO2uw; e2mf=6547cb11874742649b40a78898e73d83; rem=on; casgwusercred=Mx47IHy5maDxjyUP7UiZmB9ZoN5iRRMuOrq8hsm-e-gMC69ARyW2X2XslZW4MlDfjNofNvZadCn7BzCJLZhOogd95b9a2379e86f292aa116ac13e159aa; 91c8d7964b724d0a_gr_cs1=f5c97b2d8a463135818d7f2d00c000d8; 964de61476ecd75d_gr_session_id=d5c8fecb-c5d2-4c1c-8b23-aebe8b78e9c6; 964de61476ecd75d_gr_session_id_d5c8fecb-c5d2-4c1c-8b23-aebe8b78e9c6=true");
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        client.executeMethod(method);
        System.out.println(method.getResponseBodyAsString());
        System.out.println(param);
        System.out.println(emailList.size());
        emailList.clear();
    }

    @Data
    public static class QueryOutOrderParam {
        Integer schoolId;
        String purchaseCode;
        Integer deliverCompanyType;
        Integer warehouseId;
    }
}
