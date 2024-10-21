package ufpa.libertapp.experienciatrabalho;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExperienciaTrabalhoRepository extends JpaRepository<ExperienciaTrabalho,Long> {
    @Query(value = "SELECT * FROM experiencia_trabalho e JOIN vitima v ON "
        + "v.cpf = e.vitima_cpf WHERE v.user_id = :userId", nativeQuery = true)
    List<ExperienciaTrabalho> findByUserId(@Param("userId") Long userId);
}
