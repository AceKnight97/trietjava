package Group4.DigitalCV.services;

import Group4.DigitalCV.model.*;
import Group4.DigitalCV.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OtherSkillService {

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

    public void updateOtherSkill(DigitalCV data, DigitalCV existData) {
        List<OtherSkill> newSkill = new ArrayList<>();
        for (OtherSkill item : data.getOtherSkills()) {
            Long itemId = item.getId();
            OtherSkill saveItem;
            if (itemId != null) {
                OtherSkill curItem = otherSkillRepo.findById(itemId).get();
                OtherSkill updateItem = curItem.updateModel(curItem, item);
                saveItem = otherSkillRepo.save(updateItem);
            } else {
                saveItem = otherSkillRepo.save(item);
            }
            saveItem.setDigitalCV(existData);
            newSkill.add(saveItem);
        }
        for (OtherSkill item : existData.getOtherSkills()) {
            Long itemId = item.getId();
            // System.out.println("itemId: " + itemId);
            Boolean isItemInOldData = data.getOtherSkills().stream().anyMatch(e -> e.getId() == itemId);
            // System.out.println("isItemInOldData: " + isItemInOldData);
            if (isItemInOldData == false) {
                item.setDigitalCV(null);
                otherSkillRepo.delete(item);
            }
        }
        existData.setOtherSkills(newSkill);
    }

}
