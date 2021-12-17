package com.demoDigital.demo.controllers;

import com.demoDigital.demo.customModel.DeleteCV;
import com.demoDigital.demo.model.DigitalCV;
import com.demoDigital.demo.model.MutationResponse;
import com.demoDigital.demo.model.OtherSkill;
import com.demoDigital.demo.repository.DigitalCVRepository;
import com.demoDigital.demo.services.DigitalCVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/digitalcv")
public class DigitalCVController {

    @Autowired
    DigitalCVService digitalCVService;

    // GET
    @GetMapping("/{id}")
    public DigitalCV getCV(@PathVariable Long id) {
        return digitalCVService.getDigitalCV(id);
    }

    @GetMapping()
    public List<DigitalCV> getCVs() {
        return digitalCVService.getDigitalCVs();
    }

    @GetMapping("/email/{email}")
    public List<DigitalCV> getCVsByEmail(@PathVariable String email) {
        return digitalCVService.getCVsByEmail(email);
    }

    @GetMapping("/test")
    public List<OtherSkill> getTest() {
        return digitalCVService.getTest();
    }

    public MutationResponse checkUsernameJobTitle(DigitalCV data) {
        MutationResponse response = new MutationResponse();
        String username = data.getPersonalInfo().getUsername();
        String jobTitle = data.getJobTitle();
        if (username == null || jobTitle == null) {
            response.isSuccess = false;
            response.message = "No username or job title";
            return response;
        }
        return response;
    }

    // POST
    @PostMapping("/createcv")
    public MutationResponse createcv(@RequestBody DigitalCV data) {
        MutationResponse response = this.checkUsernameJobTitle(data);
        if (response.isSuccess == false) {
            return response;
        }
        DigitalCV saveData = digitalCVService.createDigitalCV(data);
        response.isSuccess = saveData != null;
        response.data = saveData;
        return response;
    }

    // PUST
    @PutMapping("/updatecv/{cv_id}")
    public MutationResponse updateDigitalCV(@RequestBody DigitalCV data, @PathVariable Long cv_id) {
        MutationResponse response = this.checkUsernameJobTitle(data);
        if (response.isSuccess == false) {
            return response;
        }
        DigitalCV saveData = digitalCVService.updateDigitalCV(data, cv_id);
        response.isSuccess = saveData != null;
        response.data = saveData;
        return response;
    }

    @PutMapping("/deletecv/{cv_id}")
    public MutationResponse deleteDigitalCV(@RequestBody DeleteCV body, @PathVariable Long cv_id) {
        String email = body.email;
        MutationResponse response = digitalCVService.deleteDigitalCV(email, cv_id);
        return response;
    }

}
