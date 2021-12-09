package com.demoDigital.demo.controllers;

import com.demoDigital.demo.model.DigitalCV;
import com.demoDigital.demo.repository.DigitalCVRespository;
import com.demoDigital.demo.services.DigitalCVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/digitalcv")
public class DigitalCVController {

    @Autowired
    DigitalCVService digitalCVService;

    @GetMapping("/{id}")
    public DigitalCV getCV(@PathVariable Long id) {
        return digitalCVService.getDigitalCV(id);
    }

    @GetMapping()
    public List<DigitalCV> getCVs() {
        return digitalCVService.getDigitalCVs();
    }
}
