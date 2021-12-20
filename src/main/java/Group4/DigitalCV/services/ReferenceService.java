package Group4.DigitalCV.services;

import Group4.DigitalCV.model.*;
import Group4.DigitalCV.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReferenceService {

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

    public void updateReference(DigitalCV data, DigitalCV existData) {
        List<ReferenceCV> newArray = new ArrayList<>();
        for (ReferenceCV item : data.getReferencecvs()) {
            Long itemId = item.getId();
            ReferenceCV saveItem;
            if (itemId != null) {
                ReferenceCV curItem = referenceCVRepo.findById(itemId).get();
                ReferenceCV updateItem = curItem.updateModel(curItem, item);
                saveItem = referenceCVRepo.save(updateItem);
            } else {
                saveItem = referenceCVRepo.save(item);
            }
            saveItem.setDigitalCV(existData);
            newArray.add(saveItem);
        }
        for (ReferenceCV item : existData.getReferencecvs()) {
            Long itemId = item.getId();
            Boolean isItemInOldData = data.getReferencecvs().stream().anyMatch(e -> e.getId() == itemId);
            if (isItemInOldData == false) {
                item.setDigitalCV(null);
                referenceCVRepo.delete(item);
            }
        }
        existData.setReferencecvs(newArray);
    }

}
