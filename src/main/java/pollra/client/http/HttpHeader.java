package main.java.pollra.client.http;

import java.util.HashMap;
import java.util.Map;

public class HttpHeader {
    private String protocol;
    private HttpStatus status;
    private Map<String, String> responseHeader = new HashMap<>();

    public HttpHeader() { }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Map<String, String> getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(Map<String, String> responseHeader) {
        this.responseHeader = responseHeader;
    }

    public void addResponseHeader(String key, String value){
        this.responseHeader.put(key, value);
    }

    public HttpHeader(String protocol, HttpStatus status, Map<String, String> responseHeader) {
        this.protocol = protocol;
        this.status = status;
        this.responseHeader = responseHeader;
    }

    @Override
    public String toString() {
        return "HttpHeader{" +
                "protocol='" + protocol + '\'' +
                ", status=" + status +
                ", responseHeader=" + responseHeader +
                '}';
    }
}
