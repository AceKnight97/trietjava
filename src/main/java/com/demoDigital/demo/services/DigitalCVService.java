package com.demoDigital.demo.services;


import com.demoDigital.demo.model.DigitalCV;
import com.demoDigital.demo.model.PersonalInfo;
import com.demoDigital.demo.repository.DigitalCVRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigitalCVService {

    @Autowired
    DigitalCVRespository digitalCVRepo;
    PersonalInfo personalInfoRepo;
// GET
    public DigitalCV getDigitalCV(Long id){
        return digitalCVRepo.getById(id);
    }
    public List<DigitalCV> getDigitalCVs(){
        return digitalCVRepo.findAll();
    }
//    POST
    public DigitalCV createDigitalCV(DigitalCV data){
        return digitalCVRepo.save(data);
    }
    public DigitalCV updateDigitalCV(DigitalCV data){
        DigitalCV existData = digitalCVRepo.getById(data.getId());

        // UPDATE BASIC
        existData.setHobby(data.getHobby());
        existData.setPhoto(data.getPhoto());
        existData.setJobTitle(data.getJobTitle());
        existData.setCvType(data.getCvType());

        // PersonalInfo existPersonalInfo = personalInfoRepo.get(data.getPersonalInfo()))
        // UPDATE PERSONAL INFO
        // existData.se


        return digitalCVRepo.save(existData);
    }
//    PUT


//    DELETE


}
