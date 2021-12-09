package com.demoDigital.demo.repository;

// import java.util.List;

import com.demoDigital.demo.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherSkillsRespository extends JpaRepository<Certificate, Long> {


}
