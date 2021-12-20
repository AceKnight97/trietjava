package Group4.DigitalCV.services;

import Group4.DigitalCV.model.*;
import Group4.DigitalCV.repository.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonalInfoService {
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
    public PersonalInfo getPersonalInfo(Long id) {
        return personalInfoRepo.findById(id).get();
    }

    public List<PersonalInfo> getPersonalInfos() {
        return personalInfoRepo.findAll();
    }

}
