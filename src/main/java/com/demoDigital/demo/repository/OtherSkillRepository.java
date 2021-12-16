package com.demoDigital.demo.repository;

import com.demoDigital.demo.model.OtherSkill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherSkillRepository extends JpaRepository<OtherSkill, Long> {

    @Query(value = "DELETE * FROM other_skills skil where skil.id = :id", nativeQuery = true)
    void deleteSkill(Long id);
}
