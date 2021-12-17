package com.demoDigital.demo.services;

import java.util.List;

import com.demoDigital.demo.model.Certificate;
import com.demoDigital.demo.model.DigitalCV;
import com.demoDigital.demo.model.Education;
import com.demoDigital.demo.model.MutationResponse;
import com.demoDigital.demo.model.OtherSkill;
import com.demoDigital.demo.model.PersonalInfo;
import com.demoDigital.demo.model.ProgrammingLanguage;
import com.demoDigital.demo.model.Project;
import com.demoDigital.demo.model.ReferenceCV;
import com.demoDigital.demo.model.WorkingExperience;
import com.demoDigital.demo.repository.CertificateRepository;
import com.demoDigital.demo.repository.DigitalCVRepository;
import com.demoDigital.demo.repository.EducationRepository;
import com.demoDigital.demo.repository.OtherSkillRepository;
import com.demoDigital.demo.repository.PersonalInfoRepository;
import com.demoDigital.demo.repository.ProgrammingRepository;
import com.demoDigital.demo.repository.ProjectRepository;
import com.demoDigital.demo.repository.ReferenceCVRepository;
import com.demoDigital.demo.repository.WorkingExperienceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DigitalCVService {

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

    @Autowired
    OtherSkillService otherSkillService;
    @Autowired
    EducationService educationService;
    @Autowired
    WorkingExperienceService workingExperienceService;
    @Autowired
    ReferenceService referenceService;
    @Autowired
    CertificateService certificateService;
    @Autowired
    ProjectService projectService;
    @Autowired
    ProgrammingLanguageService programmingLanguageService;

    // GET
    public DigitalCV getDigitalCV(Long id) {
        try {
            DigitalCV res = digitalCVRepo.findById(id).get();
            return res;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Failed to get CVid: " + id);
            System.out.println("Err: " + e);
        }
        return null;
    }

    public List<DigitalCV> getDigitalCVs() {
        return digitalCVRepo.findAll();
    }

    public List<OtherSkill> getTest() {
        return otherSkillRepo.findAll();
    }

    public List<DigitalCV> getCVsByEmail(String email) {
        return digitalCVRepo.getByEmail(email);
    }

    // POST
    public DigitalCV createDigitalCV(DigitalCV data) {
        String email = data.getPersonalInfo().getEmail();
        PersonalInfo personCV = personalInfoRepo.findByEmail(email);
        if (personCV == null) {
            System.out.println("personCV == null");
            personCV = data.getPersonalInfo();
            personalInfoRepo.save(personCV);
        } else {
            System.out.println("personCV NOT null");
            data.setPersonalInfo(personCV);
            digitalCVRepo.save(data);
        }
        DigitalCV newDigitalCV = digitalCVRepo.save(data);
        newDigitalCV.setIsActive(true);

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

    // PUT
    public DigitalCV updateDigitalCV(DigitalCV data, Long id) {
        try {
            DigitalCV existData = digitalCVRepo.findById(id).get();

            // UPDATE BASIC
            existData.setHobby(data.getHobby());
            existData.setJobTitle(data.getJobTitle());
            // UPDATE Image
            // existData.setPhoto(data.getPhoto());
            // existData.setCvType(data.getCvType());
            // existData.setIsActive(data.getIsActive());
            // UPDATE PERSONAL INFO
            PersonalInfo curPerson = existData.getPersonalInfo();
            personalInfoRepo.save(curPerson.updateModel(curPerson, data.getPersonalInfo()));
            existData.setPersonalInfo(curPerson);

            // UPDATE EDUCATION
            educationService.updateEducation(data, existData);

            // UPDATE CERTIFICATE
            certificateService.updateCertificate(data, existData);

            // UPDATE WORKING EXPERIENCE
            workingExperienceService.updateWorkingExp(data, existData);

            // UPDATE REFERENCE
            referenceService.updateReference(data, existData);

            // UPDATE OTHER SKILLS
            otherSkillService.updateOtherSkill(data, existData);

            // UPDATE PROJECT
            projectService.updateProject(data, existData);

            // UPDATE PROGRAMMING LANGUAGES
            projectService.updateProject(data, existData);

            return digitalCVRepo.save(existData);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Err: " + e);
        }
        return null;
    }

    public MutationResponse validateUpdateCV(String email, Long id, DigitalCV existData) {
        MutationResponse response = new MutationResponse();
        String cvEmail = existData.getPersonalInfo().getEmail();
        Boolean isRightEmail = cvEmail.equals(email);
        if (existData == null || !isRightEmail) {
            System.out.println("cvEmail: " + cvEmail);
            System.out.println("email: " + email);
            System.out.println("isRightEmail: " + isRightEmail);
            response.isSuccess = false;
            response.message = "No exist data or wrong user email!";
            return response;
        }
        return response;
    }

    public MutationResponse changeCVType(String email, Long id, String cvType) {
        DigitalCV existData = digitalCVRepo.findById(id).get();
        MutationResponse response = this.validateUpdateCV(email, id, existData);
        if (response.isSuccess == false) {
            return response;
        }
        if (cvType == null || cvType == "") {
            response.isSuccess = false;
            response.message = "No type to change!";
            return response;
        }
        existData.setCvType(cvType);
        DigitalCV data = digitalCVRepo.save(existData);
        response.isSuccess = data != null;
        response.data = data;
        return response;
    }

    public MutationResponse changePhoto(String email, Long id, String photo) {
        DigitalCV existData = digitalCVRepo.findById(id).get();
        MutationResponse response = this.validateUpdateCV(email, id, existData);
        if (response.isSuccess == false) {
            return response;
        }
        if (photo == null || photo == "") {
            response.isSuccess = false;
            response.message = "No photo to change!";
            return response;
        }
        existData.setPhoto(photo);
        DigitalCV data = digitalCVRepo.save(existData);
        response.isSuccess = data != null;
        response.data = data;
        return response;
    }

    // DELETE
    public MutationResponse deleteDigitalCV(String email, Long id) {
        DigitalCV existData = digitalCVRepo.findById(id).get();
        MutationResponse response = this.validateUpdateCV(email, id, existData);
        existData.setIsActive(false);
        response.isSuccess = digitalCVRepo.save(existData) != null;
        return response;
    }

}
