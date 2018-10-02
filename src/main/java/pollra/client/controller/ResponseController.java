package main.java.pollra.client.controller;


import main.java.pollra.client.Resolver.LoginResolver;
import main.java.pollra.client.Resolver.ViewResolver;
import main.java.pollra.client.http.HttpHeader;
import main.java.pollra.client.http.HttpRequest;
import main.java.pollra.client.http.HttpResponse;
import main.java.pollra.client.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 클라이언트에서 Request객체를 받은 뒤
 * 요청에 맞는 HttpHeader를 생성
 */
public class ResponseController {

    private HttpRequest httpRequest;

    public ResponseController(HttpRequest httpRequest){
        this.httpRequest = httpRequest;
    }

    public HttpHeader createHeader(int bodyLength){
        HttpHeader httpHeader = new HttpHeader();
        /**
         * 입력된 URI 가 정상적인지 여부에 따라서 HttpStatus 가 바뀌어야 함
         */
        /**
         * 리스폰스 바디가 없을때
         * 정상적이다? : 아직 정해진 기준이 없음
         */
        httpHeader.setProtocol("HTTP/1.1");
        httpHeader.setStatus(HttpStatus.OK);
        httpHeader.addResponseHeader("Content-Type", "text/html;charset=utf-8");
        httpHeader.addResponseHeader("Content-Length", bodyLength + "");
        if(httpRequest.getBody() != null){
            Map<String, String> user = new HashMap<>();
            String requestUser = new String(httpRequest.getBody());
            for(String temp : requestUser.split("&")){
                user.put(temp.split("=")[0],temp.split("=")[1]);
            }
            System.out.println(user.get("email")+user.get("pass"));

            LoginResolver loginResolver = new LoginResolver();
            switch (loginResolver.loginAction(user.get("email"), user.get("pass"))){
                case 0:
                    System.out.println("존재하지 않는 아이디");
                    break;
                case 1:
                    System.out.println("비밀번호 틀림");
                    break;
                case 2:
                    System.out.println("로그인 성공");

                    break;
            }
        }

        return httpHeader;
    }



}
