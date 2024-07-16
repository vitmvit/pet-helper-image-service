package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, String> {

    Optional<Avatar> findByGeneratedName(String name);
}