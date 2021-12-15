package com.demoDigital.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoDigital.demo.model.Certificate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

}
