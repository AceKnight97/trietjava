package com.demoDigital.demo.repository;

// import java.util.List;

import com.demoDigital.demo.model.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRespository extends JpaRepository<PersonalInfo, Long> {

}
