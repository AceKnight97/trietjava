package trietjava.javalang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import trietjava.javalang.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT  * FROM USERS WHERE email = :email", nativeQuery = true)
	User getUsersByEmail(String email);

	User findByEmail(String email);

	User findById(String Id);

}
