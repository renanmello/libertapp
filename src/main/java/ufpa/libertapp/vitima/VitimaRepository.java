package ufpa.libertapp.vitima;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VitimaRepository extends JpaRepository<Vitima, String> {
    Optional<Vitima> findByUserId(Long userId);
}
