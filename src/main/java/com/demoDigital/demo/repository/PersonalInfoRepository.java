package com.demoDigital.demo.repository;

import com.demoDigital.demo.model.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {

  PersonalInfo findByEmail(String email);

}
