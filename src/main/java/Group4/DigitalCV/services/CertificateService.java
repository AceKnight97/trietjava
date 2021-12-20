package Group4.DigitalCV.services;

import Group4.DigitalCV.model.*;
import Group4.DigitalCV.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CertificateService {

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

    public void updateCertificate(DigitalCV data, DigitalCV existData) {
        List<Certificate> newArray = new ArrayList<>();
        for (Certificate item : data.getCertificates()) {
            Long itemId = item.getId();
            Certificate saveItem;
            if (itemId != null) {
                Certificate curItem = certificateRepo.findById(itemId).get();
                Certificate updateItem = curItem.updateModel(curItem, item);
                saveItem = certificateRepo.save(updateItem);
            } else {
                saveItem = certificateRepo.save(item);
            }
            saveItem.setDigitalCV(existData);
            newArray.add(saveItem);
        }
        for (Certificate item : existData.getCertificates()) {
            Long itemId = item.getId();
            Boolean isItemInOldData = data.getCertificates().stream().anyMatch(e -> e.getId() == itemId);
            if (isItemInOldData == false) {
                item.setDigitalCV(null);
                certificateRepo.delete(item);
            }
        }
        existData.setCertificates(newArray);
    }

}
