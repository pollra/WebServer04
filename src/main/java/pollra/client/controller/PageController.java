package main.java.pollra.client.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class PageController{

    private String[][] uriList = {
            {"/","/index","/index.html"},
            {"/indexOk", "/indexOk.html","/indexOk"},
            {"/Ok.html"},
            {"/No.html"}
    };

    private PageController(){}
    public static PageController getInstanse(){ return SingletonHolder.instanse; }
    public static class SingletonHolder { private static PageController instanse = new PageController(); }

    public byte[] PageController(String uri) throws IOException{
        byte[] result = Files.readAllBytes(new File("src/main/resources/page/error/404.html").toPath());
        switch (foundPage(uri)){
            case 0:
                result = Files.readAllBytes(new File("src/main/resources/page/index.html").toPath());
                break;
            case 1:
                result = Files.readAllBytes(new File("src/main/resources/page/indexOk.html").toPath());
                break;
            case 2:
                result = Files.readAllBytes(new File("src/main/resources/page/login/Ok.html").toPath());
                break;
            case 3:
                result = Files.readAllBytes(new File("src/main/resources/page/login/No.html").toPath());
                break;
        }
        return result;
    }

    public int foundPage(String uri){
        for(int i =0; i<uriList.length; i++){
            for(int j=0; j<uriList[i].length; j++){
                if(uriList[i][j].equals(uri)){
                    return i;
                }
            }
        }
        return -1;
    }

}
