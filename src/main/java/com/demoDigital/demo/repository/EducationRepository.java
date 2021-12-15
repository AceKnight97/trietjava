package com.demoDigital.demo.repository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import com.demoDigital.demo.model.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

}
