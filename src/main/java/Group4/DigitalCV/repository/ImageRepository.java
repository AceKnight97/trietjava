package Group4.DigitalCV.repository;

import java.util.Optional;
import Group4.DigitalCV.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
}
