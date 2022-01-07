package com.demoDigital.demo.services;

import java.util.List;

import com.demoDigital.demo.customModel.CreateUserRequest;
import com.demoDigital.demo.model.PersonalInfo;
import com.demoDigital.demo.repository.CertificateRepository;
import com.demoDigital.demo.repository.DigitalCVRepository;
import com.demoDigital.demo.repository.EducationRepository;
import com.demoDigital.demo.repository.OtherSkillRepository;
import com.demoDigital.demo.repository.PersonalInfoRepository;
import com.demoDigital.demo.repository.ProgrammingRepository;
import com.demoDigital.demo.repository.ProjectRepository;
import com.demoDigital.demo.repository.ReferenceCVRepository;
import com.demoDigital.demo.repository.WorkingExperienceRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    // POST
    public PersonalInfo createUser(CreateUserRequest user) {
        String email = user.getEmail();
        PersonalInfo newUser = personalInfoRepo.findByEmail(email);
        if (newUser != null) {
            System.out.println("newUser already exist!");
            return null;
        }
        // System.out.println("newUser: " + user.getEmail() + " " + user.getUsername() +
        // " " + user.getPassword());
        PersonalInfo existUser = new PersonalInfo();
        existUser.setUsername(user.getUsername());
        existUser.setEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        existUser.setPassword(encoder.encode(user.getPassword()));
        return personalInfoRepo.save(existUser);
    }

}
