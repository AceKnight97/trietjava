package Group4.DigitalCV.repository;

import Group4.DigitalCV.model.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {

  @Query(value = "SELECT * FROM personal_info info where info.email LIKE :email", nativeQuery= true)
  PersonalInfo findByEmail(String email);

}
