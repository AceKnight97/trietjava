package com.demoDigital.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoDigital.demo.model.ReferenceCV;

@Repository
public interface ReferenceCVRepository extends JpaRepository<ReferenceCV, Long> {

}
