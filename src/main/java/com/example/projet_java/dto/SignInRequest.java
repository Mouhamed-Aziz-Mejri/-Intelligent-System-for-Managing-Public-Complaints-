package com.example.projet_java.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data

public class SignInRequest {
    private String userName;
    private String password;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
