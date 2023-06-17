package com.ust.client.domain;

public class JwtRequest {
   // @NotEmpty(message = "Please enter your username")
    private String userName;
    //@NotEmpty(message = "Please enter your password")
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
