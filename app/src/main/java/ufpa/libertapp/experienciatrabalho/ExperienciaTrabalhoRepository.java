package ufpa.libertapp.experienciatrabalho;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExperienciaTrabalhoRepository extends JpaRepository<ExperienciaTrabalho,Long> {

    @Query(value = "select * from experiencia_trabalho e join vitima v on v.cpf = e.vitima_cpf  where v.user_id = :userId", nativeQuery = true)
    ExperienciaTrabalho findByUserId(@Param("userId") Long id);
}
