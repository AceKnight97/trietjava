package Group4.DigitalCV.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Group4.DigitalCV.model.ProgrammingLanguage;

@Repository
public interface ProgrammingRepository extends JpaRepository<ProgrammingLanguage, Long> {

}
