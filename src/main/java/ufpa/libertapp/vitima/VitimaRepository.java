package ufpa.libertapp.vitima;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VitimaRepository extends JpaRepository<Vitima, String> {

    @Query("SELECT v FROM Vitima v WHERE v.user.id = :userId")
    Vitima findByUserId(@Param("userId") Long userId);
}
