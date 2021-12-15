package com.demoDigital.demo.services;

import com.demoDigital.demo.model.*;
import com.demoDigital.demo.repository.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DigitalCVService {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    DigitalCVRepository digitalCVRepo;
    @Autowired
    PersonalInfoRepository personalInfoRepo;
    @Autowired
    EducationRepository educationRespo;
    @Autowired
    CertificateRepository certificateRepo;
    @Autowired
    ReferenceCVRepository referenceCVRepo;
    @Autowired
    ProgrammingRepository programmingRepo;
    @Autowired
    WorkingExperienceRepository workingExperienceRepo;
    @Autowired
    ProjectRepository projectRepo;
    @Autowired
    OtherSkillRepository otherSkillRepo;

    // GET
    public DigitalCV getDigitalCV(Long id) {
        return digitalCVRepo.findById(id).get();
    }

    public List<DigitalCV> getDigitalCVs() {
        return digitalCVRepo.findAll();
    }

    // POST
    public DigitalCV createDigitalCV(DigitalCV data) {
        DigitalCV newDigitalCV = digitalCVRepo.save(data);
        PersonalInfo personCV = personalInfoRepo.findByEmail(data.getPersonalInfo().getEmail());
        if(personCV == null){
            personCV = data.getPersonalInfo();
            personalInfoRepo.save(personCV);
        }
        List<DigitalCV> lisDigitalCVs = personCV.getDigitalcvs();
        lisDigitalCVs.add(newDigitalCV);
        personalInfoRepo.save(personCV);
        
        for (Education item : newDigitalCV.getEducations()) {
            item.setDigitalCV(newDigitalCV);
            educationRespo.save(item);
        }
        for (Certificate item : newDigitalCV.getCertificates()) {
            item.setDigitalCV(newDigitalCV);
            certificateRepo.save(item);
        }
        for (ReferenceCV item : newDigitalCV.getReferencecvs()) {
            item.setDigitalCV(newDigitalCV);
            referenceCVRepo.save(item);
        }
        for (ProgrammingLanguage item : newDigitalCV.getProgrammingLanguages()) {
            item.setDigitalCV(newDigitalCV);
            programmingRepo.save(item);
        }
        for (WorkingExperience item : newDigitalCV.getWorkingExperiences()) {
            item.setDigitalCV(newDigitalCV);
            workingExperienceRepo.save(item);
        }
        for (Project item : newDigitalCV.getProjects()) {
            item.setDigitalCV(newDigitalCV);
            projectRepo.save(item);
        }
        for (OtherSkill item : newDigitalCV.getOtherSkills()) {
            item.setDigitalCV(newDigitalCV);
            otherSkillRepo.save(item);
        }

        digitalCVRepo.save(newDigitalCV);
        return newDigitalCV;
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
