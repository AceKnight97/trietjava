package Group4.DigitalCV.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Group4.DigitalCV.customModel.ManagementCV;
import Group4.DigitalCV.model.Certificate;
import Group4.DigitalCV.model.DigitalCV;
import Group4.DigitalCV.model.Education;
import Group4.DigitalCV.model.MutationResponse;
import Group4.DigitalCV.model.OtherSkill;
import Group4.DigitalCV.model.PersonalInfo;
import Group4.DigitalCV.model.ProgrammingLanguage;
import Group4.DigitalCV.model.Project;
import Group4.DigitalCV.model.ReferenceCV;
import Group4.DigitalCV.model.WorkingExperience;
import Group4.DigitalCV.repository.CertificateRepository;
import Group4.DigitalCV.repository.DigitalCVRepository;
import Group4.DigitalCV.repository.EducationRepository;
import Group4.DigitalCV.repository.OtherSkillRepository;
import Group4.DigitalCV.repository.PersonalInfoRepository;
import Group4.DigitalCV.repository.ProgrammingRepository;
import Group4.DigitalCV.repository.ProjectRepository;
import Group4.DigitalCV.repository.ReferenceCVRepository;
import Group4.DigitalCV.repository.WorkingExperienceRepository;

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

    public List<ManagementCV> getCVManagement(String email) {
        List<DigitalCV> list = digitalCVRepo.getByEmail(email);
        List<ManagementCV> res = new ArrayList<>();
        for (DigitalCV item : list) {
            res.add(item.getCVManagement());
        }
        return res;
        // return digitalCVRepo.getManagementCVs(email);
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

        newDigitalCV.setIsActive(true);
        newDigitalCV.setCreatedAt(LocalDateTime.now());
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

            existData.setLastModified(LocalDateTime.now());

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
        existData.setLastModified(LocalDateTime.now());
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
        existData.setLastModified(LocalDateTime.now());
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
        existData.setLastModified(LocalDateTime.now());
        response.isSuccess = digitalCVRepo.save(existData) != null;
        return response;
    }

}
