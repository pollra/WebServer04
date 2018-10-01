package main.java;

import main.java.pollra.server.Server;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Server.getInstance().start();
    }
}
