package ufpa.libertapp.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso,Long> {
    @Query(value = "SELECT c.id, vitima_cpf, c.nome, c.conteudo, c.empresa_curso, c.horas FROM "
        + "curso c JOIN vitima v ON v.cpf = c.vitima_cpf WHERE v.user_id = :userId", nativeQuery = true)
    List<Curso> findByUserId(@Param("userId") Long userId);
}
