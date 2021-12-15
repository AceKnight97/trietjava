package com.demoDigital.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoDigital.demo.model.DigitalCV;

@Repository
public interface DigitalCVRepository extends JpaRepository<DigitalCV, Long> {

	// @Query(value = "SELECT * FROM DigitalCV WHERE isActive = :true", nativeQuery
	// = true)
	// List<DigitalCV> getActiveCVs(Boolean isActive);

}
