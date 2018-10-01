package main.java.pollra.util;

import main.java.pollra.client.http.HttpMethod;
import main.java.pollra.client.http.HttpRequest;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataSupporter {

    // rawData(HttpHeader / type: byte[]) 를 받아서 HttpRequest 를 리턴하는 메소드
    public HttpRequest byteToHttpRequest(byte[] rawData){
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
                for(String temp : data.split(" ")){
                    requestLine[count++] = temp.trim();
                }
            }
            count = 0;
            // Key: value
            for(String temp : data.split(":", 2)){
                tempArray[count++] = temp.trim();
            }
            header.put(tempArray[0], tempArray[1]);
        }

        for(Map.Entry<String, String> ent : header.entrySet()){
            if(ent.getKey().trim().equals("Content-Length")){
                System.out.println("[!]byteToHttpRequest - [Content-Length: "+ ent.getValue()+"]");

            }
        }

        HttpRequest httpRequest = builder
                .setRequestLine(requestLine)
                .setHeader(header)
                .build();
        return httpRequest;
    }

}
