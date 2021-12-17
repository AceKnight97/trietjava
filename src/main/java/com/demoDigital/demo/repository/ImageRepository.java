package com.demoDigital.demo.repository;

import java.util.Optional;
import com.demoDigital.demo.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
}
