package Group4.DigitalCV.services;

import Group4.DigitalCV.model.*;
import Group4.DigitalCV.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

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

    public void updateProject(DigitalCV data, DigitalCV existData) {
        List<Project> newArray = new ArrayList<>();
        for (Project item : data.getProjects()) {
            Long itemId = item.getId();
            Project saveItem;
            if (itemId != null) {
                Project curItem = projectRepo.findById(itemId).get();
                Project updateItem = curItem.updateModel(curItem, item);
                saveItem = projectRepo.save(updateItem);
            } else {
                saveItem = projectRepo.save(item);
            }
            saveItem.setDigitalCV(existData);
            newArray.add(saveItem);
        }
        for (Project item : existData.getProjects()) {
            Long itemId = item.getId();
            Boolean isItemInOldData = data.getProjects().stream().anyMatch(e -> e.getId() == itemId);
            if (isItemInOldData == false) {
                item.setDigitalCV(null);
                projectRepo.delete(item);
            }
        }
        existData.setProjects(newArray);
    }

}
