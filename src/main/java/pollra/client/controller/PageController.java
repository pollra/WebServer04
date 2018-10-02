package main.java.pollra.client.controller;

import main.java.pollra.util.DataSupporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class PageController{

    private DataSupporter dataSupporter = new DataSupporter();
    private Map<String, String> pageList;

    private PageController(){
        pageList = dataSupporter.dirList();
    }
    public static PageController getInstanse(){ return SingletonHolder.instanse; }
    public static class SingletonHolder { private static PageController instanse = new PageController(); }

    public byte[] PageController(String uri) throws IOException{
        return Files.readAllBytes(
                new File(
                        getPageUri(
                                foundPageName(uri)
                        )
                ).toPath());
    }

    public String getPageUri(String uri){
        System.out.println("getPageUri("+uri+")");
        for(Map.Entry<String, String> ent : pageList.entrySet()){
            if(ent.getKey().equals(uri)){
                return ent.getValue();
            }
        }
        System.out.println("ㅇㅅㅇ?:"+pageList.get("404"));
        return pageList.get("404");
    }

    public int foundPage(String uri){
        int result = 0;
        for(Map.Entry<String, String> ent : pageList.entrySet()){
            if(ent.getKey().equals(uri)) return result;
            result++;
        }
        return -1;
    }

    public String foundPageName(String uri){
        if(uri.equals("/")){return "index";}
        String result = uri.substring(1, uri.length());
        if(result.indexOf(".html") > 0){
            result = result.substring(0, result.indexOf(".html"));
        }
        return result;
    }



}
