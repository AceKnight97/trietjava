package com.demoDigital.demo.customModel;

import lombok.Data;

import com.demoDigital.demo.model.PersonalInfo;

@Data
public class AuthResponse {

    private PersonalInfo user;
    private String token;

    public PersonalInfo getUser() {
        return this.user;
    }

    public void setUser(PersonalInfo user) {
        this.user = user;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
