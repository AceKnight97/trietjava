package Group4.DigitalCV.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Group4.DigitalCV.model.ReferenceCV;

@Repository
public interface ReferenceCVRepository extends JpaRepository<ReferenceCV, Long> {

}
