package com.demoDigital.demo.controllers;

import java.util.List;

import com.demoDigital.demo.model.MutationResponse;
import com.demoDigital.demo.model.PersonalInfo;
import com.demoDigital.demo.services.PersonalInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personalinfo")
public class PersonalInfoController {

    @Autowired
    PersonalInfoService personalInfoService;

    MutationResponse response = new MutationResponse();

    // GET
    @GetMapping("/{id}")
    public PersonalInfo getPersonalInfo(@PathVariable Long id) {
        return personalInfoService.getPersonalInfo(id);
    }

    @GetMapping()
    public List<PersonalInfo> getPersonalInfos() {
        return personalInfoService.getPersonalInfos();
    }

}
