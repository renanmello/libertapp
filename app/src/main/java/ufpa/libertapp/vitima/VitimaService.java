package ufpa.libertapp.vitima;


import org.springframework.data.repository.query.Param;
import ufpa.libertapp.curso.CursoDTO;
import ufpa.libertapp.experienciatrabalho.ExperienciaDTO;

import java.util.List;

public interface VitimaService {
    Vitima viewDados(Long id);

    Vitima create(Vitima vitima, Long user_id);

    Vitima update(Vitima vitima, Long id);

    void delete(String cpf);

    List<Vitima> viewAll();

    List<VitimaDTO> findAllVitimaDetails();

    List<CursoDTO> findCursosByUserId(Long userId);

    List<ExperienciaDTO> findExperienciasByUserId(Long userId);
}
