package com.demoDigital.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoDigital.demo.model.ProgrammingLanguage;

@Repository
public interface ProgrammingRepository extends JpaRepository<ProgrammingLanguage, Long> {

}
