package com.example.springbootcommon.tools;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

public class InvokeApi {

    private static final String URL = "http://ip:8092/connService/offLineStaff?tenantId=1&userId=";
    private static final String ids = "";

    public static void main(String[] args) {
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
}
