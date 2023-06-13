package com.youtube.jwt.dto;

import com.youtube.jwt.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserRegistrationRequest {


    @NotEmpty(message = "Username is required")
    @Email(message = "Invalid email address")
    @Pattern(regexp = ".*@gmail\\.com$", message = "Email must be of format @gmail.com")
    private String userName;

    @NotEmpty(message = "First name is required")
    private String userFirstName;

    @NotEmpty(message = "Last name is required")
    private String userLastName;

    @NotEmpty(message = "Password is required")
    @ValidPassword(message = "Password criteria does not match")
    private String userPassword;
    public UserRegistrationRequest() {
    }

    public UserRegistrationRequest(String userName, String userFirstName, String userLastName, String userPassword) {
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
