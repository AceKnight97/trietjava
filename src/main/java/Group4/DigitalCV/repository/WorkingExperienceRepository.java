package Group4.DigitalCV.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Group4.DigitalCV.model.WorkingExperience;

@Repository
public interface WorkingExperienceRepository extends JpaRepository<WorkingExperience, Long> {

}
