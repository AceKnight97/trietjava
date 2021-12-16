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

    public List<DigitalCV> getCVsByEmail(String email) {
        return digitalCVRepo.getByEmail(email);
    }

    // POST
    public DigitalCV createDigitalCV(DigitalCV data) {
        String email = data.getPersonalInfo().getEmail();
        // System.out.println("check Email: "+ email);
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

        digitalCVRepo.save(newDigitalCV);
        return newDigitalCV;
    }

    public DigitalCV updateDigitalCV(DigitalCV data, Long id) {
        try {
            DigitalCV existData = digitalCVRepo.findById(id).get();

            // UPDATE BASIC
            existData.setHobby(data.getHobby());
            existData.setPhoto(data.getPhoto());
            existData.setJobTitle(data.getJobTitle());
            existData.setCvType(data.getCvType());
            // UPDATE PERSONAL INFO
            PersonalInfo curPerson = existData.getPersonalInfo();
            personalInfoRepo.save(curPerson.updateModel(curPerson, data.getPersonalInfo()));
            existData.setPersonalInfo(curPerson);

            // UPDATE EDUCATION
            List<Education> newEdu = new ArrayList<>();
            for (Education item : data.getEducations()) {
                Long itemId = item.getId();
                if (itemId != null) {
                    Education curItem = educationRespo.findById(itemId).get();
                    Education updateItem = curItem.updateModel(curItem, item);
                    newEdu.add(educationRespo.save(updateItem));
                } else {
                    newEdu.add(educationRespo.save(item));
                }
            }
            existData.setEducations(newEdu);

            // UPDATE CERTIFICATE
            List<Certificate> newCerti = new ArrayList<>();
            for (Certificate item : data.getCertificates()) {
                Long itemId = item.getId();
                if (itemId != null) {
                    Certificate curItem = certificateRepo.findById(itemId).get();
                    Certificate updateItem = curItem.updateModel(curItem, item);
                    newCerti.add(certificateRepo.save(updateItem));
                } else {
                    newCerti.add(certificateRepo.save(item));
                }
            }
            existData.setCertificates(newCerti);

            // UPDATE WORKING EXPERIENCE
            List<WorkingExperience> newWorkExp = new ArrayList<>();
            for (WorkingExperience item : data.getWorkingExperiences()) {
                Long itemId = item.getId();
                if (itemId != null) {
                    WorkingExperience curItem = workingExperienceRepo.findById(itemId).get();
                    WorkingExperience updateItem = curItem.updateModel(curItem, item);
                    newWorkExp.add(workingExperienceRepo.save(updateItem));
                } else {
                    newWorkExp.add(workingExperienceRepo.save(item));
                }
            }
            existData.setWorkingExperiences(newWorkExp);

            // UPDATE REFERENCE
            List<ReferenceCV> newRefer = new ArrayList<>();
            for (ReferenceCV item : data.getReferencecvs()) {
                Long itemId = item.getId();
                if (itemId != null) {
                    ReferenceCV curItem = referenceCVRepo.findById(itemId).get();
                    ReferenceCV updateItem = curItem.updateModel(curItem, item);
                    newRefer.add(referenceCVRepo.save(updateItem));
                } else {
                    newRefer.add(referenceCVRepo.save(item));
                }
            }
            existData.setReferencecvs(newRefer);

            // UPDATE OTHER SKILLS
            List<OtherSkill> newSkill = new ArrayList<>();
            for (OtherSkill item : data.getOtherSkills()) {
                Long itemId = item.getId();
                if (itemId != null) {
                    OtherSkill curItem = otherSkillRepo.findById(itemId).get();
                    OtherSkill updateItem = curItem.updateModel(curItem, item);
                    newSkill.add(otherSkillRepo.save(updateItem));
                } else {
                    newSkill.add(otherSkillRepo.save(item));
                }
            }
            existData.setOtherSkills(newSkill);

            // UPDATE PROJECT
            List<Project> newProject = new ArrayList<>();
            for (Project item : data.getProjects()) {
                Long itemId = item.getId();
                if (itemId != null) {
                    Project curItem = projectRepo.findById(itemId).get();
                    Project updateItem = curItem.updateModel(curItem, item);
                    newProject.add(projectRepo.save(updateItem));
                } else {
                    newProject.add(projectRepo.save(item));
                }
            }
            existData.setProjects(newProject);

            // UPDATE PROJECT
            List<ProgrammingLanguage> newProgramming = new ArrayList<>();
            for (ProgrammingLanguage item : data.getProgrammingLanguages()) {
                Long itemId = item.getId();
                if (itemId != null) {
                    ProgrammingLanguage curItem = programmingRepo.findById(itemId).get();
                    ProgrammingLanguage updateItem = curItem.updateModel(curItem, item);
                    newProgramming.add(programmingRepo.save(updateItem));
                } else {
                    newProgramming.add(programmingRepo.save(item));
                }
            }
            existData.setProgrammingLanguages(newProgramming);

            return digitalCVRepo.save(existData);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
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
