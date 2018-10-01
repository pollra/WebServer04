package main.java.pollra.util;

import main.java.pollra.client.http.HttpRequest;

import java.util.LinkedList;
import java.util.List;

public class DataSupporter {

    private DataSupporter(){ }
    public DataSupporter getInstanse() { return SingletonHolder.instanse; }
    public static class SingletonHolder{
        public static DataSupporter instanse = new DataSupporter();
    }

    // rawData(HttpHeader / type: byte[]) 를 받아서 HttpRequest 를 리턴하는 메소드
    public HttpRequest byteToHttpRequest(byte[] rawData){
        String requestLine = "";
        List<String> requestHeader = new LinkedList<>();
        for(String data : rawData.toString().split("\r\n")){
            if(requestLine == ""){
                requestLine = data;
            }
            requestHeader.add(data);
        }




        return null;
    }

}
