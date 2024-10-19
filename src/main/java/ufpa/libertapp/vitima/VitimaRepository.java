package ufpa.libertapp.vitima;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VitimaRepository extends JpaRepository<Vitima, String> {

    @Query("SELECT v FROM Vitima v WHERE v.user.id = :userId")
    Vitima findByUserId(@Param("userId") Long userId);

    @Query("SELECT v FROM Vitima v JOIN v.experiencias e WHERE LOWER(e.cargo) LIKE LOWER(CONCAT('%', :cargo, '%'))")
    List<Vitima> findByVitimaExp(@Param("cargo") String cargo);

    @Query("SELECT v FROM Vitima v JOIN v.cursos c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Vitima> findByNomeCurso(@Param("nome") String nome);

    @Query("SELECT v FROM Vitima v JOIN v.cursos c WHERE LOWER(c.conteudo) LIKE LOWER(CONCAT('%', :conteudo, '%'))")
    List<Vitima> findByConteudoCurso(@Param("conteudo") String conteudo);

    @Query("SELECT new ufpa.libertapp.vitima.VitimaDTO(v.nome, v.email, v.telefone, u.password,v.contactada) FROM Vitima v JOIN v.user u")
    List<VitimaDTO> findAllVitimaDetails();



    //nome - telefone - email - senha

}
