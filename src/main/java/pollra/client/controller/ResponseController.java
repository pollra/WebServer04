package main.java.pollra.client.controller;


import main.java.pollra.client.Resolver.ViewResolver;
import main.java.pollra.client.http.HttpHeader;
import main.java.pollra.client.http.HttpRequest;
import main.java.pollra.client.http.HttpResponse;
import main.java.pollra.client.http.HttpStatus;

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

    /*
    // Client에서 오는 Request를 객체로 만들어 저장.
    HttpRequest httpRequest = dataSupporter.bytesToHttpRequest(baos.toByteArray());
    System.out.println(httpRequest.toString());
    body = viewResolver.getUriToFileBytes(httpRequest.getUri());

    // Request 객체를 넘기고 ResponseHeader를 받는다

    // Client Response Html
    HttpResponse.ResponseBuilder builder;
    HttpStatus status = HttpStatus.NotFound;
    if(viewResolver.foundPage(httpRequest.getUri())>=0){
        status = HttpStatus.OK;
    }
    builder = new HttpResponse.ResponseBuilder(status);
    HttpResponse response = builder
            .addResponseHeader("Content-Type", "text/html;charset=utf-8")
            .addResponseHeader("Content-Length", body.length + "")
            .Builder();*/
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
            System.out.println("쿠키 만드는거");
        }

        return httpHeader;
    }



}
