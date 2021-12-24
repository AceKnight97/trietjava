package com.demoDigital.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demoDigital.demo.customModel.ManagementCV;
import com.demoDigital.demo.model.DigitalCV;

@Repository
public interface DigitalCVRepository extends JpaRepository<DigitalCV, Long> {

	public static final String JOIN_CV_PERSON = "FROM digitalcvs cv join personal_info info on cv.personal_info_id = info.id where info.email LIKE :email AND cv.is_active = TRUE";
	public static final String ACTIVE_CV = "SELECT * " + JOIN_CV_PERSON;

	@Query(value = ACTIVE_CV, nativeQuery = true)
	List<DigitalCV> getByEmail(String email);

	// cv.id, cv.created_at, cv.last_modified, cv.cv_type
	// cv.id, cv.createdAt, cv.lastModified, cv.cvType
	public static final String MANAGEMENT_CV = "SELECT new com.demoDigital.demo.customModel.ManagementCV(cv.id, cv.createdAt, cv.lastModified, cv.cvType) "
			+ JOIN_CV_PERSON;

	// public static final String MANAGEMENT_CV = "SELECT cv.id AS id, cv.created_at
	// AS createdAt, cv.last_modified AS lastModified, cv.cv_type AS cvType FROM
	// digitalcvs cv join personal_info info on cv.personal_info_id = info.id where
	// info.email = :email AND cv.is_active = TRUE";

	@Query(value = MANAGEMENT_CV, nativeQuery = true)
	List<ManagementCV> getManagementCVs(String email);

}
