package main.java.pollra.client.Resolver;

import main.java.pollra.client.http.HttpHeader;
import main.java.pollra.client.http.HttpResponse;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * Request URI 를 받아 HttpResponse.Body 에 담아 보낼 페이지를 검색
 * Controller와 ViewResolver의 정보를 조합.
 * Response 객체를 만들고, 이를 Client로 넘김
 */
public class ViewResolver {
    private final String F_defualtPath = "src/main/resources/page";
    private Map<String, String> pageList;

    private ViewResolver(){
        pageList = dirList(F_defualtPath);
    }
    public static ViewResolver getInstanse(){ return SingletonHolder.instanse; }
    public static class SingletonHolder { private static ViewResolver instanse = new ViewResolver(); }

    public byte[] getUriToFileBytes(String uri) throws IOException{
        return Files.readAllBytes(
                new File(
                        getPageUri(
                                foundPageName(uri)
                        )
                ).toPath());
    }

    public String getPageUri(String uri){
        for(Map.Entry<String, String> ent : pageList.entrySet()){
            if(ent.getKey().equals(uri)){
                return ent.getValue();
            }
        }
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
        if(uri.equals("/") || uri.equals("/favicon.ico")){ return "index"; }
        String result = uri.substring(1, uri.length());
        if(result.indexOf(".html") > 0){
            result = result.substring(0, result.indexOf(".html"));
        }
        return result;
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
//                System.out.println(file.getName().substring(0, file.getName().indexOf("."))+" : "+ source+"/" +file.getName());
                result.put(file.getName().substring(0, file.getName().indexOf(".")), source+"/" +file.getName());
            }else if(file.isDirectory()){ // 디렉토리 일 경우
                dirList(source +"/"+ file.getName());
            }
        }
        return result;
    }

    // HttpHeader 와 body를 받아서 Response 객체 생성
    public HttpResponse createHttpResponse(HttpHeader header, byte[] body){
        HttpResponse httpResponse;
        HttpResponse.ResponseBuilder builder = new HttpResponse.ResponseBuilder(header.getStatus());
        httpResponse = builder
                .setHeader(header)
                .setBody(body)
                .Builder();
        return httpResponse;
    }

    public void view(OutputStream os, byte[] body, HttpResponse response) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);
        // HTTP/1.1 200 OK
        System.out.println(response.toString());
        dos.writeBytes(response.getProtocol()
                + " "
                + response
                .getStatus()
                .getStatusMessage()
                + "\r\n");
        for(Map.Entry<String, String> ent : response.getResponseHeader().entrySet()){
            dos.writeBytes(ent.getKey()+": "+ent.getValue()+"\r\n");
        }
        dos.writeBytes("\r\n");
        dos.write(body, 0, body.length);
        dos.writeBytes("\r\n");

        dos.flush();
    }
}
