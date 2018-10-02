package main.java.pollra.client.Resolver;

import main.java.pollra.client.controller.LoginActionExample;

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
    public int loginAction(String id, String pw){
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

}
