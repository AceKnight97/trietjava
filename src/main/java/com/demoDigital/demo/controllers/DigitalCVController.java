package com.demoDigital.demo.controllers;

import com.demoDigital.demo.model.DigitalCV;
import com.demoDigital.demo.model.MutationResponse;
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

    MutationResponse response = new MutationResponse();

    // GET
    @GetMapping("/{id}")
    public DigitalCV getCV(@PathVariable Long id) {
        return digitalCVService.getDigitalCV(id);
    }

    @GetMapping()
    public List<DigitalCV> getCVs() {
        return digitalCVService.getDigitalCVs();
    }

    // POST
    @PostMapping("/createcv")
    public MutationResponse createcv(@RequestBody DigitalCV data) {
        DigitalCV saveData = digitalCVService.createDigitalCV(data);
        if (saveData == null) {
            response.isSuccess = false;
        } else {
            response.data = saveData;
        }
        return response;
    }

    // PUST
    @PutMapping("/updatecv/{id}")
    public MutationResponse createUser(@RequestBody DigitalCV data, @PathVariable Long id) {
        DigitalCV saveData = digitalCVService.updateDigitalCV(data, id);
        if (saveData == null) {
            response.isSuccess = false;
        } else {
            response.data = saveData;
        }
        return response;
    }

}
