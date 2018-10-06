package com.pollra.server;

import com.pollra.client.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port = 12345;
    private ServerSocket serverSocket;
    private ExecutorService executorService;

    private Server(){
        try{
            serverSocket = new ServerSocket(port);
            executorService = Executors.newFixedThreadPool(10);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static Server getInstance(){ return SingletonHolder.instanse; }
    private static class SingletonHolder{private static Server instanse = new Server();}

    public void start() throws IOException{

        while(true){

            Socket socket = serverSocket.accept();
            System.out.println(socket.getInetAddress()+"에서 접속 시도...");
            executorService.execute(new Client(socket));
        }
    }

    private void init(){

    }
}
