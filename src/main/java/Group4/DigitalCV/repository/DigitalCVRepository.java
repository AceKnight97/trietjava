package Group4.DigitalCV.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Group4.DigitalCV.model.DigitalCV;

@Repository
public interface DigitalCVRepository extends JpaRepository<DigitalCV, Long> {

	public static final String ACTIVE_CV = "SELECT * FROM digitalcvs cv join personal_info info on cv.personal_info_id = info.id where info.email LIKE :email AND cv.is_active = TRUE";

	@Query(value = ACTIVE_CV, nativeQuery = true)
	List<DigitalCV> getByEmail(String email);

	// public static final String MANAGEMENT_CV = "SELECT * FROM digitalcvs cv join
	// personal_info info on cv.personal_info_id = info.id where info.email LIKE
	// :email AND cv.is_active = TRUE";
	// @Query(value = MANAGEMENT_CV, nativeQuery = true)
	// List<ManagementCV> getManagementCVs(String email);

}
