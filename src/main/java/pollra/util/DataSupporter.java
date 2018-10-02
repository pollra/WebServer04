package main.java.pollra.util;

import main.java.pollra.client.http.HttpMethod;
import main.java.pollra.client.http.HttpRequest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataSupporter {

    private final String F_defualtPath = "src/main/resources/page";

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

    // input URI, return file(type : byte[])
    public byte[] uriToFileByteArray(String uri) throws IOException {
        return Files.readAllBytes(new File(F_defualtPath+uri).toPath());
    }

    // 특정 디렉토리에 있는 파일 목록 읽고 Map<fileName, filePath> 리턴
    // defualt Path : src/main/resources/page
    public Map<String, String> dirList(String source){
        Map<String, String> result = new HashMap<>();
        File dir = new File(source);
        File[] fileList = dir.listFiles();
        for(int i=0; i<fileList.length; i++){
            File file = fileList[i];
            if(file.isFile()){ // 파일일 경우
                System.out.println(file.getName().substring(0, file.getName().indexOf("."))+" : "+ source+"/" +file.getName());
                result.put(file.getName().substring(0, file.getName().indexOf(".")), source+"/" +file.getName());
            }else if(file.isDirectory()){ // 디렉토리 일 경우
                dirList(source +"/"+ file.getName());
            }
        }
        return result;
    }

    public Map<String, String> dirList(){
        return dirList(F_defualtPath);
    }
}
