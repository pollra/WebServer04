package main.java.pollra.client.http;

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

        public HttpRequest builder(){
            HttpRequest httpRequest= new HttpRequest();
            httpRequest.method = method;
            httpRequest.uri = uri;
            httpRequest.protocol = protocol;
            httpRequest.header = header;
            return httpRequest;
        }
    }
}
