package com.example.springbootcommon.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.IOException;
import java.text.DecimalFormat;

public class KfSession {

    private static final String URL = "http://10.7.41.192:8080/workspace/auth/user/login";

    public static void main(String[] args) {
        try{
            StringBuilder tmpcookies = new StringBuilder();
            DecimalFormat df=new DecimalFormat("000");
            for (int i = 1; i <= 100; i++) {
                String name = "test" + df.format(i);
                tmpcookies.append(name).append("\t").append(invokeOnce(name)).append("\r\n");
            }
            System.out.println(tmpcookies.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String invokeOnce(String name) throws IOException {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(URL);
        RequestEntity se = new StringRequestEntity(new ReqParam(name).toJsonString() ,"application/json" ,"UTF-8");
        postMethod.setRequestEntity(se);
        postMethod.setRequestHeader("Content-Type","application/json");

        int httpStatus = httpClient.executeMethod(postMethod);
//        String str=postMethod.getResponseBodyAsString();

        Cookie[] cookies = httpClient.getState().getCookies();
//        StringBuilder tmpcookies = new StringBuilder();
        for (Cookie c : cookies) {
            return c.getValue();
//            tmpcookies.append(c.getValue()).append("\n\r");
//            System.out.println(tmpcookies.toString());
        }
        return "";
    }

    @Data
    @NoArgsConstructor
    static class ReqParam {
        private String username;
        private String password;
        private String companyCode;
        private String loginStatus;

        ReqParam(String username){
            this.username = username;
            this.password = "pressure";
            this.companyCode = "7000";
            this.loginStatus = "1";
        }

        String toJsonString() throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        }
    }
}
