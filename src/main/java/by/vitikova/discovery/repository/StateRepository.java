package by.vitikova.discovery.repository;

import by.vitikova.discovery.model.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, String> {

    Optional<State> findByGeneratedName(String name);
}