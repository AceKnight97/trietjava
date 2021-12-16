package com.demoDigital.demo.services;

import com.demoDigital.demo.model.*;
import com.demoDigital.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkingExperienceService {

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

    public void updateWorkingExp(DigitalCV data, DigitalCV existData) {
        List<WorkingExperience> newArray = new ArrayList<>();
        for (WorkingExperience item : data.getWorkingExperiences()) {
            Long itemId = item.getId();
            WorkingExperience saveItem;
            if (itemId != null) {
                WorkingExperience curItem = workingExperienceRepo.findById(itemId).get();
                WorkingExperience updateItem = curItem.updateModel(curItem, item);
                saveItem = workingExperienceRepo.save(updateItem);
            } else {
                saveItem = workingExperienceRepo.save(item);
            }
            saveItem.setDigitalCV(existData);
            newArray.add(saveItem);
        }
        for (WorkingExperience item : existData.getWorkingExperiences()) {
            Long itemId = item.getId();
            Boolean isItemInOldData = data.getWorkingExperiences().stream().anyMatch(e -> e.getId() == itemId);
            if (isItemInOldData == false) {
                item.setDigitalCV(null);
                workingExperienceRepo.delete(item);
            }
        }
        existData.setWorkingExperiences(newArray);
    }

}
