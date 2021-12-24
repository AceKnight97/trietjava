package com.demoDigital.demo.controllers;

import java.util.List;

import com.demoDigital.demo.customModel.ManagementCV;
import com.demoDigital.demo.customModel.UpdateCV;
import com.demoDigital.demo.model.DigitalCV;
import com.demoDigital.demo.model.MutationResponse;
import com.demoDigital.demo.model.OtherSkill;
import com.demoDigital.demo.model.PersonalInfo;
import com.demoDigital.demo.services.DigitalCVService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/management/{email}")
    public List<ManagementCV> getCVManagement(@PathVariable String email) {
        return digitalCVService.getCVManagement(email);
    }

    @GetMapping("/test")
    public List<OtherSkill> getTest() {
        return digitalCVService.getTest();
    }

    public MutationResponse checkValidPhoto(DigitalCV data) {
        MutationResponse response = new MutationResponse();
        String photo = data.getPhoto().toLowerCase();
        Boolean isValidPhoto = photo.contains("data:image/jpeg;base64") || photo.contains("data:image/png;base64");
        if (!isValidPhoto) {
            response.isSuccess = false;
            response.message = "Invalid image type!";
            return response;
        }
        return response;
    }

    public MutationResponse checkUsernameJobTitle(DigitalCV data) {
        MutationResponse response = new MutationResponse();
        PersonalInfo person = data.getPersonalInfo();
        String username = person.getUsername();
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
        System.out.println(data.toString());
        MutationResponse response = this.checkUsernameJobTitle(data);
        if (response.isSuccess == false) {
            return response;
        }
        response = this.checkValidPhoto(data);
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
    public MutationResponse deleteDigitalCV(@RequestBody UpdateCV body, @PathVariable Long cv_id) {
        String email = body.email;
        MutationResponse response = digitalCVService.deleteDigitalCV(email, cv_id);
        return response;
    }

    @PutMapping("/changetype/{cv_id}")
    public MutationResponse changeCVType(@RequestBody UpdateCV body, @PathVariable Long cv_id) {
        String email = body.email;
        String cvType = body.cvType;
        MutationResponse response = digitalCVService.changeCVType(email, cv_id, cvType);
        return response;
    }

    @PutMapping("/changephoto/{cv_id}")
    public MutationResponse changePhoto(@RequestBody UpdateCV body, @PathVariable Long cv_id) {
        MutationResponse response = new MutationResponse();
        try {
            String email = body.email;
            String photo = body.photo.toLowerCase();
            Boolean isValidPhoto = photo.contains("data:image/jpeg;base64") || photo.contains("data:image/png;base64");
            if (!isValidPhoto) {
                response.isSuccess = false;
                response.message = "Invalid image type!";
                return response;
            }
            response = digitalCVService.changePhoto(email, cv_id, photo);
            return response;
        } catch (Exception e) {
            // TODO: handle exception
            response.isSuccess = false;
        }
        return response;
    }

}
