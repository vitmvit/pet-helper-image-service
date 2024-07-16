package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

    Optional<Image> findByGeneratedName(String name);
}