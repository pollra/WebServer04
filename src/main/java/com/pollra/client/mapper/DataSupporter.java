package com.pollra.client.mapper;

import com.pollra.client.http.HttpRequest;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 클라이언트에서 받은 Request를 객체화 시켜서
 * 다시 클라이언트로 보내주는 역할을 한다.
 */
public class DataSupporter {

    // rawData(HttpHeader / type: byte[]) 를 받아서 HttpRequest 를 리턴하는 메소드
    public HttpRequest bytesToHttpRequest(byte[] rawData){
        HttpRequest.RequestBuilder builder = new HttpRequest.RequestBuilder();
        int count;
        // method / uri / protocol
        String requestLine[] = new String[3];
        String tempArray[] = new String[2];
        Map<String, String> header = new HashMap<>();

        String rawData_String = new String(rawData, Charset.forName("UTF-8"));
        for(String data : rawData_String.split("\r\n")){
            count = 0;
            // GET / HTTP/1.1
            if(requestLine[0] == null){
                for(String temp : data.split(" ")){ requestLine[count++] = temp.trim(); }
            }
            count = 0;
            // Key: value
            for(String temp : data.split(":", 2)){ tempArray[count++] = temp.trim(); }
            header.put(tempArray[0], tempArray[1]);
        }

        for(Map.Entry<String, String> ent : header.entrySet()){
            if(ent.getKey().trim().equals("Content-Length")){
                HttpRequest httpRequest = builder
                        .setRequestLine(requestLine)
                        .setHeader(header)
                        .setBody(rawData_String.substring(rawData_String.length()-Integer.parseInt(ent.getValue())).getBytes())
                        .build();
                return httpRequest;
            }
        }

        HttpRequest httpRequest = builder
                .setRequestLine(requestLine)
                .setHeader(header)
                .build();
        return httpRequest;
    }

}
