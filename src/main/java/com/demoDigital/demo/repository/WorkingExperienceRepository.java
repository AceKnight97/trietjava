package com.demoDigital.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoDigital.demo.model.WorkingExperience;

@Repository
public interface WorkingExperienceRepository extends JpaRepository<WorkingExperience, Long> {

}
