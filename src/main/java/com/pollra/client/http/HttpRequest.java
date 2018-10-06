package com.pollra.client.http;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private HttpMethod method;
    private String uri;
    private String protocol;
    private Map<String, String> header;
    private byte[] body;

    public HttpMethod getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getProtocol() {
        return protocol;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public byte[] getBody() {
        return body;
    }

    public static class RequestBuilder{
        private HttpMethod method;
        private String uri;
        private String protocol;
        private Map<String, String> header = new HashMap<>();
        private byte[] body;

        public RequestBuilder setRequestLine(String[] requestLine){
            if(requestLine.length <= 3) {
                this.method = HttpMethod.valueOf(requestLine[0]);
                this.uri = requestLine[1];
                this.protocol = requestLine[2];
            }else{
                System.out.println("[!] Error: setRequestLine Exception");
            }
            return this;
        }

        public RequestBuilder setMethod(HttpMethod method) {
            this.method = method;
            return this;
        }

        public RequestBuilder setUri(String uri) {
            this.uri = uri;
            return this;
        }

        public RequestBuilder setProtocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public RequestBuilder setHeader(Map<String, String> header) {
            this.header = header;
            return this;
        }

        public RequestBuilder setBody(byte[] body) {
            this.body = body;
            return this;
        }

        public HttpRequest build(){
            HttpRequest httpRequest= new HttpRequest();
            httpRequest.method = method;
            httpRequest.uri = uri;
            httpRequest.protocol = protocol;
            httpRequest.header = header;
            httpRequest.body = body;
            return httpRequest;
        }
    }


    @Override
    public String toString() {
        return "HttpRequest{" +
                "method=" + method +
                ", uri='" + uri + '\'' +
                ", protocol='" + protocol + '\'' +
                ", header=" + header +
                ", body=" + (
                                body == null?   // 타입에 따라서도 바꿔줘야함
                                        "no Data" : new String(body)
                            ) +
                '}';
    }
}
