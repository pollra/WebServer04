package main.java.pollra.client;

import main.java.pollra.client.http.HttpRequest;
import main.java.pollra.client.http.HttpResponse;
import main.java.pollra.client.http.HttpStatus;
import main.java.pollra.util.DataSupporter;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.*;

public class Client implements Runnable{
    private Socket socket;
    private final int BUF_SIZE = 8192;

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
            byte[] body = Files.readAllBytes(new File("src/main/resources/page/index.html").toPath());

            int len;

            while (true) {
                len = dis.read(buffer);
                baos.write(Arrays.copyOfRange(buffer, 0, len));

                if (len != BUF_SIZE) {
                    break;
                }
            }
            // rawData 를 HttpReqeust 객체로 만들어서 저장.
            final byte[] rawData = baos.toByteArray();

            // 값들을 모두 불러들인 뒤 DataSupporter 로 빌드
            DataSupporter dataSupporter = new DataSupporter();
            HttpRequest httpRequest = dataSupporter.byteToHttpRequest(baos.toByteArray());
            System.out.println(httpRequest.toString());

            // Client Response Html
            HttpResponse.ResponseBuilder builder = new HttpResponse.ResponseBuilder(HttpStatus.OK);
            HttpResponse response = builder
                    .addResponseHeader("Content-Type", "text/html;charset=utf-8")
                    .addResponseHeader("Content-Length", body.length + "")
                    .Builder();
            DataOutputStream dos = new DataOutputStream(os);
            // HTTP/1.1 200 OK
            dos.writeBytes(response.getProtocol() + " " + response.getStatus().getStatusMessage() + "\r\n");
            for(Map.Entry<String, String> ent : response.getResponseHeader().entrySet()){
                dos.writeBytes(ent.getKey()+": "+ent.getValue()+"\r\n");
            }
            dos.writeBytes("\r\n");
            dos.write(body, 0, body.length);
            dos.writeBytes("\r\n");

            dos.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
