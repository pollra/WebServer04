package main.java.pollra.client;

import main.java.pollra.client.Resolver.ViewResolver;
import main.java.pollra.client.controller.ResponseController;
import main.java.pollra.client.http.HttpHeader;
import main.java.pollra.client.http.HttpRequest;
import main.java.pollra.client.http.HttpResponse;
import main.java.pollra.client.mapper.DataSupporter;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Client implements Runnable{
    private Socket socket;
    private final int BUF_SIZE = 8192;
    private ResponseController responseController;

    public Client(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try(InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream()){
            final byte[] buffer = new byte[BUF_SIZE];
            DataInputStream dis = new DataInputStream(is);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataSupporter dataSupporter = new DataSupporter();      // 데이터 서포터
            ViewResolver viewResolver = ViewResolver.getInstanse();
            byte[] body;
            int len;

            while (true) {
                len = dis.read(buffer);
                baos.write(Arrays.copyOfRange(buffer, 0, len));
                if (len != BUF_SIZE) {
                    break;
                }
            }
            final byte[] rawData = baos.toByteArray();

            // Client에서 오는 Request를 객체로 만들어 저장.
            HttpRequest httpRequest = dataSupporter.bytesToHttpRequest(baos.toByteArray());
            System.out.println(httpRequest.toString());

            body = viewResolver.getUriToFileBytes(httpRequest.getUri());

            responseController = new ResponseController(httpRequest);
            HttpHeader httpHeader = responseController.createHeader(body.length);

            HttpResponse response = viewResolver.createHttpResponse(httpHeader, body);

            viewResolver.view(os, body, response);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
