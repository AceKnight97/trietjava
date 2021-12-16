package com.demoDigital.demo.services;

import com.demoDigital.demo.model.*;
import com.demoDigital.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EducationService {

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

    public void updateEducation(DigitalCV data, DigitalCV existData) {
        List<Education> newArray = new ArrayList<>();
        for (Education item : data.getEducations()) {
            Long itemId = item.getId();
            Education saveItem;
            if (itemId != null) {
                Education curItem = educationRespo.findById(itemId).get();
                Education updateItem = curItem.updateModel(curItem, item);
                saveItem = educationRespo.save(updateItem);
            } else {
                saveItem = educationRespo.save(item);
            }
            saveItem.setDigitalCV(existData);
            newArray.add(saveItem);
        }
        for (Education item : existData.getEducations()) {
            Long itemId = item.getId();
            Boolean isItemInOldData = data.getEducations().stream().anyMatch(e -> e.getId() == itemId);
            if (isItemInOldData == false) {
                item.setDigitalCV(null);
                educationRespo.delete(item);
            }
        }
        existData.setEducations(newArray);
    }

}
