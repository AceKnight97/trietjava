package com.demoDigital.demo.services;

import com.demoDigital.demo.model.*;
import com.demoDigital.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgrammingLanguageService {

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

    public void updateProgramming(DigitalCV data, DigitalCV existData) {
        List<ProgrammingLanguage> newArray = new ArrayList<>();
        for (ProgrammingLanguage item : data.getProgrammingLanguages()) {
            Long itemId = item.getId();
            ProgrammingLanguage saveItem;
            if (itemId != null) {
                ProgrammingLanguage curItem = programmingRepo.findById(itemId).get();
                ProgrammingLanguage updateItem = curItem.updateModel(curItem, item);
                saveItem = programmingRepo.save(updateItem);
            } else {
                saveItem = programmingRepo.save(item);
            }
            saveItem.setDigitalCV(existData);
            newArray.add(saveItem);
        }
        for (ProgrammingLanguage item : existData.getProgrammingLanguages()) {
            Long itemId = item.getId();
            Boolean isItemInOldData = data.getProgrammingLanguages().stream().anyMatch(e -> e.getId() == itemId);
            if (isItemInOldData == false) {
                item.setDigitalCV(null);
                programmingRepo.delete(item);
            }
        }
        existData.setProgrammingLanguages(newArray);
    }

}
