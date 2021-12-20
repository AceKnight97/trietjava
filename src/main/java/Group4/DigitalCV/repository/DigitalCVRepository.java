package Group4.DigitalCV.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import Group4.DigitalCV.model.DigitalCV;

@Repository
public interface DigitalCVRepository extends JpaRepository<DigitalCV, Long> {

	@Query(value = "SELECT * FROM digitalcvs cv join personal_info info on cv.personal_info_id = info.id where info.email LIKE :email AND cv.is_active = TRUE", nativeQuery = true)
	List<DigitalCV> getByEmail(String email);

}
