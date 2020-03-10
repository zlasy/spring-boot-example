package com.example.springbootcommon.tools;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

public class InvokeApi {

    public static void main(String[] args) {
        closeSession();
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
        String ids = "10143948,10143946,10143949,10143967,10144661,10144664,10144673,10144666,10144668,10144675,10144711,10144712,10144715,10144716,10144717,10144732,10144737,10144738,10144761,10144771,10144772,10144763,10144778,10144779,10144815,10144848,10144882,10144883,10144922,10145044,10145115,10145116,10145120,10145124,10145141,10145142,10145146,10145149,10145173,10145174,10145211,10145212,10145214,10145213,10145188,10145207,10145215,10145189,10145208,10145216,10145209,10145210,10145178,10145179,10145190,10145180,10145217,10145241,10145222,10145242,10145223,10145224,10145231,10145232,10145225,10145233,10145226,10145243,10145234,10145218,10145235,10145219,10145227,10145228,10145229,10145230,10145220,10145271,10145251,10145244,10145254,10145272,10145252,10145253,10145257,10145245,10145256,10145246,10145258,10145255,10145273,10145259,10145247,10145274,10145275,10145260,10145276,10145277,10145261,10145248,10145278,10145262,10145279,10145263,10145249,10145250,10145280,10145301,10145291,10145302,10145303,10145236,10145264,10145237,10145304,10145266,10145292,10145238,10145265,10145267,10145239,10145305,10145293,10145306,10145307,10145308,10145309,10145294,10145295,10145310,10145321,10145322,10145323,10145324,10145325,10145296,10145268,10145326,10145269,10145240,10145281,10145270,10145327,10145328,10145297,10145298,10145329,10145299,10145311,10145300,10145330,10145331,10145341,10145342,10145343,10145312,10145332,10145313,10145333,10145282,10145344,10145314,10145283,10145315,10145284,10228133,10228128,10228144,10228129,10228130,10228134,10228145,10228135,10228156,10228136,10228161,10228157,10228162";
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
