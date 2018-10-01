package main.java.pollra.client;

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

            final byte[] rawData = baos.toByteArray();

            // 값들을 모두 불러들인 뒤 RequestBuilder 로 빌더




        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
