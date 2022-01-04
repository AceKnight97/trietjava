package com.demoDigital.demo.services;

import com.demoDigital.demo.auth.JwtTokenUtil;
import com.demoDigital.demo.model.PersonalInfo;
import com.demoDigital.demo.repository.PersonalInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    PersonalInfoRepository personRepo;

    public PersonalInfo authUser(String token) {
        // System.out.println("Token: " + token);
        // Get jwt token and validate
        if (!jwtTokenUtil.validate(token)) {
            System.out.println("Invalid Token!");
            return null;
        }
        PersonalInfo user = personRepo.findById(jwtTokenUtil.getUserId(token)).get();
        return user;
    }
}
