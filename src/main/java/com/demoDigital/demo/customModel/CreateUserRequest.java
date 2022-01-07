package com.demoDigital.demo.customModel;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
// import java.util.Set;

@Data
public class CreateUserRequest {

    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;

    // private Set<String> authorities;
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
