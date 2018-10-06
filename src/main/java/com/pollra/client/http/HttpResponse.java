package com.pollra.client.http;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private String protocol;
    private HttpStatus status;
    private Map<String, String> responseHeader;
    private byte[] body;

    private HttpResponse(){};

    public String getProtocol() {return protocol;}

    public HttpStatus getStatus() {return status;}

    public Map<String, String> getResponseHeader() {return responseHeader;}

    public byte[] getBody() {return body;}

    public void setBody(byte[] body) {
        this.body = body;
    }

    public static class ResponseBuilder{
        private String protocol = "HTTP/1.1";
        private HttpStatus status;
        private Map<String, String> responseHeader = new HashMap<>();
        private byte[] body;

        public ResponseBuilder(HttpStatus status) { this.status = status; }

        public ResponseBuilder setProtocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public ResponseBuilder addResponseHeader(String key, String value) {
            this.responseHeader.put(key, value);
            return this;
        }

        public ResponseBuilder setHeader(HttpHeader header) {
            this.setProtocol(header.getProtocol());
            this.responseHeader = header.getResponseHeader();
            for(Map.Entry<String, String> ent : header.getResponseHeader().entrySet()){
                this.responseHeader.put(ent.getKey(), ent.getValue());
            }
            return this;
        }

        public ResponseBuilder setBody(byte[] body) {
            this.body = body;
            return this;
        }

        public HttpResponse Builder(){
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.protocol = protocol;
            httpResponse.status = status;
            httpResponse.responseHeader = responseHeader;
            return httpResponse;
        }
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "protocol='" + protocol + '\'' +
                ", status=" + status +
                ", responseHeader=" + responseHeader +
                ", body=" + Arrays.toString(body) +
                '}';
    }
}
