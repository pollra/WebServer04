package com.pollra.client.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * DB 가 연결되어있다는 가정 하에 데이터를 저장해두고
 * get과 set을 통해 데이터를 가지고오고, 가져가는 테스트코드
 */
public class LoginActionExample {
    private Map<String, String> User = new HashMap<>();
    public LoginActionExample(){
        User.put("com/pollra", "admin");
        User.put("testid","testpw");
    }

    public Map<String, String> getUser() {
        return User;
    }

    public void setUser(Map<String, String> user) {
        User = user;
    }
}
