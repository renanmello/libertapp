package ufpa.libertapp.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositório para operações de banco de dados relacionadas à entidade {@link Curso}.
 * <p>
 * Inclui métodos de consulta personalizados para buscar cursos associados a um usuário específico.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
public interface CursoRepository extends JpaRepository<Curso, Long> {
    /**
     * Busca todos os cursos associados a um usuário específico pelo ID do usuário.
     *
     * @param userId o ID do usuário cujos cursos serão recuperados
     * @return uma lista de objetos {@link Curso} associados ao usuário
     */
    @Query(value = "SELECT c.id,vitima_cpf,c.nome,c.conteudo,c.empresa_curso,c.horas FROM "
        + "curso c JOIN vitima v ON v.cpf = c.vitima_cpf WHERE v.user_id = :userId", nativeQuery = true)
    List<Curso> findByUserId(@Param("userId") Long userId);
}
