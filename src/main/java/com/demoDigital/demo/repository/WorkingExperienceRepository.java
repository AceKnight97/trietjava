package com.demoDigital.demo.repository;

// import java.util.List;s

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;s
// import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import com.demoDigital.demo.model.WorkingExperience;

@Repository
public interface WorkingExperienceRepository extends JpaRepository<WorkingExperience, Long> {

}
