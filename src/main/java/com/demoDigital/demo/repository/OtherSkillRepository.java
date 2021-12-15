package com.demoDigital.demo.repository;

// import java.util.List;

import com.demoDigital.demo.model.OtherSkill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherSkillRepository extends JpaRepository<OtherSkill, Long> {

}
