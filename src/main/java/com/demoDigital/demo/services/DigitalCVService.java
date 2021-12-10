package com.demoDigital.demo.services;

import com.demoDigital.demo.model.DigitalCV;
import com.demoDigital.demo.model.Education;
import com.demoDigital.demo.repository.DigitalCVRespository;
// import com.demoDigital.demo.repository.PersonalInfoRespository;
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
    // PersonalInfoRespository personalInfoRepo;
    EducationRespository educationRespo;

    // GET
    public DigitalCV getDigitalCV(Long id) {
        return digitalCVRepo.getById(id);
    }

    public List<DigitalCV> getDigitalCVs() {
        return digitalCVRepo.findAll();
    }

    // POST
    public DigitalCV createDigitalCV(DigitalCV data) {
        return digitalCVRepo.save(data);
    }

    public DigitalCV updateDigitalCV(DigitalCV data, Long id) {
        DigitalCV existData = digitalCVRepo.getById(id);

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
                Education curEdu = educationRespo.getById(education.getId());
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
    public Boolean deleteDigitalCV(Long id) {
        DigitalCV existData = digitalCVRepo.getById(id);
        if (existData == null) {
            return false;
        }
        existData.setIsActive(false);
        digitalCVRepo.save(existData);
        return true;
    }

}
