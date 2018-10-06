package com.pollra.client.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HttpHeader {
    private String protocol;
    private com.pollra.client.http.HttpStatus status;
    private Map<String, String> responseHeader = new HashMap<>();

    public void addResponseHeader(String key, String value){
        this.responseHeader.put(key, value);
    }
}
