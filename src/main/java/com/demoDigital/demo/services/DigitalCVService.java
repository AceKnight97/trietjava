package com.demoDigital.demo.services;

import com.demoDigital.demo.model.DigitalCV;
import com.demoDigital.demo.model.Education;
import com.demoDigital.demo.model.MutationResponse;
import com.demoDigital.demo.repository.DigitalCVRespository;
import com.demoDigital.demo.repository.EducationRespository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DigitalCVService {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    DigitalCVRespository digitalCVRepo;
    EducationRespository educationRespo;

    // GET
    public DigitalCV getDigitalCV(Long id) {
        return digitalCVRepo.findById(id).get();
    }

    public List<DigitalCV> getDigitalCVs() {
        return digitalCVRepo.findAll();
    }

    // POST
    public DigitalCV createDigitalCV(DigitalCV data) {
        return digitalCVRepo.save(data);
    }

    public DigitalCV updateDigitalCV(DigitalCV data, Long id) {
        DigitalCV existData = digitalCVRepo.findById(id).get();

        // UPDATE BASIC
        existData.setHobby(data.getHobby());
        existData.setPhoto(data.getPhoto());
        existData.setJobTitle(data.getJobTitle());
        existData.setCvType(data.getCvType());
        // UPDATE PERSONAL INFO
        existData.setPersonalInfo(data.getPersonalInfo());

        // UPDATE EDUCATION
        // existData.setEducations(data.getEducations());
        List<Education> newEdu = new ArrayList<>();
        List<Education> existEdu = existData.getEducations();

        for (Education education : data.getEducations()) {
            if (existEdu.stream().anyMatch(e -> e.getId() == education.getId())) {
                Education curEdu = educationRespo.findById(education.getId()).get();
                Education updateEdu = modelMapper.map(curEdu, Education.class);
                newEdu.add(educationRespo.save(updateEdu));
            } else {
                newEdu.add(educationRespo.save(education));
            }
        }
        existData.setEducations(newEdu);

        return digitalCVRepo.save(existData);
    }
    // PUT

    // DELETE
    public MutationResponse deleteDigitalCV(Long id) {
        DigitalCV existData = digitalCVRepo.findById(id).get();
        MutationResponse response = new MutationResponse();
        if (existData == null) {
            response.isSuccess = false;
            response.message = "No exist data!";
            return response;
        }
        existData.setIsActive(false);
        digitalCVRepo.save(existData);
        response.isSuccess = true;
        return response;
    }

}
