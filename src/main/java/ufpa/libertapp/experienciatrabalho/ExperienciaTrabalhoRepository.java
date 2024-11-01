package ufpa.libertapp.experienciatrabalho;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositório para operações de banco de dados relacionadas à entidade {@link ExperienciaTrabalho}.
 * <p>
 * Inclui métodos de consulta personalizados para buscar experiências de trabalho
 * associadas a um usuário específico.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
public interface ExperienciaTrabalhoRepository extends JpaRepository<ExperienciaTrabalho, Long> {
    /**
     * Busca todas as experiências de trabalho associadas a um usuário pelo ID do usuário.
     *
     * @param userId o ID do usuário cujas experiências de trabalho serão recuperadas
     * @return uma lista de objetos {@link ExperienciaTrabalho} associados ao usuário
     */
    //@Query(value = "select * from experiencia_trabalho e join vitima v on v.cpf = e.vitima_cpf  where v.user_id = :userId", nativeQuery = true)
    //List<ExperienciaTrabalho> findByUserId(@Param("userId") Long id);
    @Query(value = "SELECT * FROM experiencia_trabalho e JOIN vitima v ON v.cpf = e.vitima_cpf WHERE v.user_id = :userId", nativeQuery = true)
    List<ExperienciaTrabalho> findByUserId(@Param("userId") Long userId);
}
