package com.pollra.client.db.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private String userId;
    private String userPw;
    private boolean userConnect;
}
