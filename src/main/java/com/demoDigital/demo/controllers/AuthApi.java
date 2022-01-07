package com.demoDigital.demo.controllers;

import javax.validation.Valid;

import com.demoDigital.demo.auth.JwtTokenUtil;
import com.demoDigital.demo.customModel.AuthRequest;
import com.demoDigital.demo.customModel.AuthResponse;
import com.demoDigital.demo.customModel.CreateUserRequest;
import com.demoDigital.demo.model.MutationResponse;
import com.demoDigital.demo.model.PersonalInfo;
import com.demoDigital.demo.repository.PersonalInfoRepository;
import com.demoDigital.demo.services.PersonalInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Authentication")
@RestController
@RequestMapping(path = "api/public")
@RequiredArgsConstructor
public class AuthApi {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    PersonalInfoRepository personalRepo;

    @Autowired
    PersonalInfoService personalInfoService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("login")
    public MutationResponse login(@RequestBody @Valid AuthRequest request) {
        MutationResponse res = new MutationResponse();
        AuthResponse auth = new AuthResponse();
        String email = request.getEmail();
        String password = request.getPassword();
        // System.out.println("email: " + email);
        // System.out.println("password: " + password);

        PersonalInfo user = personalRepo.findByEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        encoder.matches(password, user.getPassword());

        if (email.equalsIgnoreCase(user.getEmail()) && encoder.matches(password, user.getPassword())) {
            String token = jwtTokenUtil.generateAccessToken(user);
            System.out.println("Login success, token: " + token);
            auth.setUser(user);
            auth.setToken(token);
            res.data = auth;
        } else {
            System.out.println("Login failed!");
            res.isSuccess = false;
        }
        return res;
    }

    @PostMapping("register")
    public MutationResponse register(@RequestBody @Valid CreateUserRequest request) {
        MutationResponse res = new MutationResponse();
        PersonalInfo newUser = personalInfoService.createUser(request);

        if (newUser == null) {
            res.isSuccess = false;
            res.message = "Email already existed!";
            return res;
        }
        return res;
    }

}
