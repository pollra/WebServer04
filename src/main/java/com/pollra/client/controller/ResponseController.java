package com.pollra.client.controller;


import com.pollra.client.Resolver.LoginResolver;
import com.pollra.client.http.HttpHeader;
import com.pollra.client.http.HttpRequest;
import com.pollra.client.http.HttpStatus;

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
            LoginResolver loginResolver = new LoginResolver();
            Map<String, String> login = loginResolver.loginAction(httpRequest.getBody());
            for(Map.Entry<String, String> ent : login.entrySet()){
                httpHeader.addResponseHeader(ent.getKey(), ent.getValue());
            }
        }


        return httpHeader;
    }
}
