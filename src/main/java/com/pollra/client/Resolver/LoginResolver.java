package com.pollra.client.Resolver;

import com.pollra.client.controller.LoginActionExample;

import java.util.HashMap;
import java.util.Map;

public class LoginResolver {

    private LoginActionExample actionExample = new LoginActionExample();

    public LoginResolver(){

    }

    // 로그인 정보를 찾은 뒤 그에 맞는 int값을 리턴
    /**
     * 0: Login data is not found
     * 1: id is ok. but does not match password
     * 2: id, password OK. Login
     */
    public int loginStatus(String id, String pw){
        for(Map.Entry<String, String> ent : actionExample.getUser().entrySet()){
            if(ent.getKey().equals(id)){
                if(ent.getValue().equals(pw)){
                    return 2;
                }
                return 1;
            }
        }
        return 0;
    }

    public Map<String, String> loginAction(byte[] body){
        Map<String, String> user = new HashMap<>();
        Map<String, String> result = new HashMap<>();
        String requestUser = new String(body);
        System.out.println(requestUser);
        for(String temp : requestUser.split("&")){
            user.put(temp.split("=")[0],temp.split("=")[1]);
        }

        LoginResolver loginResolver = new LoginResolver();
        switch (loginResolver.loginStatus(user.get("email"), user.get("pass"))){
            case 0:
                System.out.println("존재하지 않는 아이디");
                break;
            case 1:
                System.out.println("비밀번호 틀림");
                break;
            case 2:
                System.out.println("로그인 성공");
                result.put("Set-Cookie","Login-cookie=login:"+user.get("email"));
                break;
        }
        return result;
    }


}
