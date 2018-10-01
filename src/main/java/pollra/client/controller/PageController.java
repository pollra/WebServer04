package main.java.pollra.client.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class PageController{

    private String[] uriList = {
            "/index.html", "/index.html",
            "/login/No.html", "/login/Ok.html"
    };

    private PageController(){}
    public PageController getInstanse(){ return SingletonHolder.instanse; }
    public static class SingletonHolder { public static PageController instanse = new PageController(); }

    public byte[] PageController(String uri) throws IOException{
        byte[] error404 = Files.readAllBytes(new File("src/main/resources/page/error/404.html").toPath());
        String[] target = {"/","/index","/index.html"};
        for(String temp : target){
            if(temp.equals(uri)){
                return Files.readAllBytes(new File("src/main/resources/page"+uri).toPath());
            }
        }
        return error404;
    }

}
