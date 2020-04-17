package com.example.springbootcommon.tools;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

public class InvokeApi {

    public static void main(String[] args) throws UnsupportedEncodingException {

        int n = 16;
        n |= n >>> 3;
        System.out.println(n);

//        kfoffline();
//        closeSession();
    }

    public static void printBytes() {

        List<Long> roomMembers = Arrays.asList(1L,3L,6L,9L,12L,34562L,12205L,35L);
        Map<Integer, List<Long>> roomGroups = new HashMap<>(32);
        roomMembers.forEach(m -> {
            Integer db = (int)(m % 32);
            if (!roomGroups.containsKey(db)){
                roomGroups.put(db, new ArrayList<>());
            }
            roomGroups.get(db).add(m);
        });
        System.out.println(roomMembers.stream().collect(Collectors.groupingBy(m -> {
            return (int) (m % 32);
        })));

        System.out.println(roomMembers.stream().map(Long::intValue).collect(Collectors.toList()));
//        System.out.println(roomGroups);
    }


    public static void kfoffline() {
        final String URL = "http://10.7.41.186:8092/connService/offLineStaff?tenantId=1&userId=";
        final String ids = "";
        try{
            String[] idList = ids.split(",");
            for (String s : idList) {
                HttpClient client = new HttpClient();
                HttpMethod method = new GetMethod(URL + s);
                method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
                try {
                    client.executeMethod(method);
                    System.out.println(method.getResponseBodyAsString());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    method.releaseConnection();
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void closeSession(){
        String ids = "11498164";
        String url = "http://10.7.41.186:8092/sessionService/closeSession?tenantId=1&connId=0&sessionId=%s&overType=5&message=clear";
        String[] list = ids.split(",");
        for (String s : list) {
            HttpClient client = new HttpClient();
            HttpMethod method = new GetMethod(String.format(url, s));
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            try {
                client.executeMethod(method);
                System.out.println(method.getResponseBodyAsString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                method.releaseConnection();
            }
        }
    }
}
