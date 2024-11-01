package ufpa.libertapp.vitima;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ufpa.libertapp.curso.CursoDTO;
import ufpa.libertapp.experienciatrabalho.ExperienciaDTO;

import java.util.List;

/**
 * Repositório para operações de banco de dados relacionadas à entidade {@link Vitima}.
 * <p>
 * Inclui métodos de consulta personalizados para buscar vítimas por ID de usuário,
 * detalhes de cursos e experiências associadas, e detalhes específicos de vítimas.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
public interface VitimaRepository extends JpaRepository<Vitima, String> {
    /**
     * Busca uma vítima pelo ID do usuário associado.
     *
     * @param userId o ID do usuário
     * @return o objeto {@link Vitima} associado ao usuário
     */
    @Query("SELECT v FROM Vitima v WHERE v.user.id = :userId")
    Vitima findByUserId(@Param("userId") Long userId);

    /**
     * Busca vítimas que possuem uma experiência de trabalho com o cargo especificado.
     *
     * @param cargo o cargo a ser buscado (parcialmente correspondente)
     * @return uma lista de objetos {@link Vitima} com a experiência de trabalho correspondente
     */
    @Query("SELECT v FROM Vitima v JOIN v.experiencias e WHERE LOWER(e.cargo) LIKE LOWER(CONCAT('%', :cargo, '%'))")
    List<Vitima> findByVitimaExp(@Param("cargo") String cargo);

    /**
     * Busca vítimas que possuem cursos com o nome especificado.
     *
     * @param nome o nome do curso a ser buscado (parcialmente correspondente)
     * @return uma lista de objetos {@link Vitima} com o curso correspondente
     */
    @Query("SELECT v FROM Vitima v JOIN v.cursos c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Vitima> findByNomeCurso(@Param("nome") String nome);

    /**
     * Busca vítimas que possuem cursos com o conteúdo especificado.
     *
     * @param conteudo o conteúdo do curso a ser buscado (parcialmente correspondente)
     * @return uma lista de objetos {@link Vitima} com cursos que correspondem ao conteúdo especificado
     */
    @Query("SELECT v FROM Vitima v JOIN v.cursos c WHERE LOWER(c.conteudo) LIKE LOWER(CONCAT('%', :conteudo, '%'))")
    List<Vitima> findByConteudoCurso(@Param("conteudo") String conteudo);

    /**
     * Recupera detalhes de todas as vítimas, incluindo nome, email, telefone, senha temporária e status de contato.
     *
     * @return uma lista de {@link VitimaDTO} com detalhes específicos de cada vítima
     */
    //@Query("SELECT new ufpa.libertapp.vitima.VitimaDTO(v.nome, v.email, v.telefone, u.password,v.contactada) FROM Vitima v JOIN v.user u")
    //List<VitimaDTO> findAllVitimaDetails();
    @Query("SELECT new ufpa.libertapp.vitima.VitimaDTO(v.nome, v.email, v.telefone, pr.temp_password, v.contactada) " +
            "FROM Vitima v " +
            "JOIN v.user u " +
            "JOIN PasswordResetToken pr ON u.id = pr.user.id")
    List<VitimaDTO> findAllVitimaDetails();

    /**
     * Busca cursos associados a um usuário específico pelo ID do usuário.
     *
     * @param userId o ID do usuário
     * @return uma lista de {@link CursoDTO} representando os cursos do usuário
     */
    @Query("SELECT new ufpa.libertapp.curso.CursoDTO(c.id, c.nome, c.empresa_curso, c.horas, c.conteudo) " +
            "FROM Vitima v " +
            "JOIN v.cursos c " +
            "WHERE v.user.id = :userId")
    List<CursoDTO> findCursosByUserId(@Param("userId") Long userId);

    /**
     * Busca experiências de trabalho associadas a um usuário específico pelo ID do usuário.
     *
     * @param userId o ID do usuário
     * @return uma lista de {@link ExperienciaDTO} representando as experiências de trabalho do usuário
     */
    @Query("SELECT new ufpa.libertapp.experienciatrabalho.ExperienciaDTO(e.id, e.nomeDaEmpresa, e.cargo, e.dataInicio, e.dataFim) " +
            "FROM Vitima v " +
            "JOIN v.experiencias e " +
            "WHERE v.user.id = :userId")
    List<ExperienciaDTO> findExperienciasByUserId(@Param("userId") Long userId);
}
