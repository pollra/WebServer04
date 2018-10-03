package main.java.pollra.client.db.user;

public class UserVO {
    private String userId;
    private String userPw;
    private boolean userConnect;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public boolean isUserConnect() {
        return userConnect;
    }

    public void setUserConnect(boolean userConnect) {
        this.userConnect = userConnect;
    }

    public UserVO(String userId, String userPw, boolean userConnect) {
        this.userId = userId;
        this.userPw = userPw;
        this.userConnect = userConnect;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userConnect=" + userConnect +
                '}';
    }
}
