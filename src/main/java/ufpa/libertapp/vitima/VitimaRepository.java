package ufpa.libertapp.vitima;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ufpa.libertapp.curso.CursoDTO;
import ufpa.libertapp.experienciatrabalho.ExperienciaDTO;

import java.util.List;

public interface VitimaRepository extends JpaRepository<Vitima, String> {
    @Query("SELECT v FROM Vitima v WHERE v.user.id = :userId")
    Vitima findByUserId(@Param("userId") Long userId);

    @Query("SELECT v FROM Vitima v JOIN v.experiencias e WHERE LOWER(e.cargo) LIKE LOWER(CONCAT('%', :cargo, '%'))")
    List<Vitima> findByVitimaExp(@Param("cargo") String cargo);

    @Query("SELECT v FROM Vitima v JOIN v.cursos c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Vitima> findByNomeCurso(@Param("nome") String nome);

    @Query("SELECT v FROM Vitima v JOIN v.cursos c WHERE LOWER(c.conteudo) LIKE LOWER(CONCAT('%', :conteudo, '%'))")
    List<Vitima> findByConteudoCurso(@Param("conteudo") String conteudo);

    //@Query("SELECT new ufpa.libertapp.vitima.VitimaDTO(v.nome, v.email, v.telefone, u.password,v.contactada) FROM Vitima v JOIN v.user u")
    //List<VitimaDTO> findAllVitimaDetails();
    @Query("SELECT new ufpa.libertapp.vitima.VitimaDTO(v.nome, v.email, v.telefone, pr.temp_password, v.contactada) "
        + "FROM Vitima v JOIN v.user u JOIN PasswordResetToken pr ON u.id = pr.user.id")
    List<VitimaDTO> findAllVitimaDetails();

    @Query("SELECT new ufpa.libertapp.curso.CursoDTO(c.id, c.nome, c.empresa_curso, c.horas, c.conteudo) "
        + "FROM Vitima v JOIN v.cursos c WHERE v.user.id = :userId")
    List<CursoDTO> findCursosByUserId(@Param("userId") Long userId);

    @Query("SELECT new ufpa.libertapp.experienciatrabalho.ExperienciaDTO(e.id, e.nomeDaEmpresa, e.cargo, e.dataInicio, e.dataFim) "
        + "FROM Vitima v JOIN v.experiencias e WHERE v.user.id = :userId")
    List<ExperienciaDTO> findExperienciasByUserId(@Param("userId") Long userId);
}
