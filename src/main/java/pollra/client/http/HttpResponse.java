package main.java.pollra.client.http;

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

    private static class ResponseBuilder{
        private String protocol = "HTTP/1.1";
        private HttpStatus status;
        private Map<String, String> responseHeader = new HashMap<>();
        private byte[] body;

        public ResponseBuilder setProtocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public ResponseBuilder setStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ResponseBuilder addResponseHeader(String key, String value) {
            this.responseHeader.put(key, value);
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
            httpResponse.body = body;
            return httpResponse;
        }
    }
}
