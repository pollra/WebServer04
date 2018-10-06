package com.pollra;

import com.pollra.server.Server;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Server.getInstance().start();
    }
}
