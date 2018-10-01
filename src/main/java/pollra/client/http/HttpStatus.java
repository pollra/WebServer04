package main.java.pollra.client.http;

public enum  HttpStatus {
    // 1xx

    // 2xx
    OK("200 OK");

    // 3xx

    // 4xx
    private String statusMessage;

    HttpStatus(String statusMessage){ this.statusMessage = statusMessage; }

    public String getStatusMessage(){ return statusMessage; }

}
