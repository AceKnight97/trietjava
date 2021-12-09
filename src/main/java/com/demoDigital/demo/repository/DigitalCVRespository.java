package com.demoDigital.demo.repository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.demoDigital.demo.model.DigitalCV;

@Repository
public interface DigitalCVRespository extends JpaRepository<DigitalCV, Long> {

	// @Query(value = "SELECT  * FROM DigitalCV WHERE isActive = :true", nativeQuery = true)
	// List<DigitalCV> getActiveCVs(Boolean isActive);


}
