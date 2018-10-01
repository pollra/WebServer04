package main.java.pollra.client.http;

public enum  HttpStatus {
    // 1xx

    // 2xx
    OK("200 OK");

    // 3xx

    // 4xx
    HttpStatus(String statusMessage){ this.statusMessage = statusMessage; }
    private String statusMessage;
    public String getStatusMessage(){ return statusMessage; }

}
