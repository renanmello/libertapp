package ufpa.libertapp.experienciatrabalho;

import ufpa.libertapp.vitima.Vitima;

import java.util.List;
import java.util.Optional;

public interface ExperienciaTrabalhoService {
    List<ExperienciaTrabalho> view(Long id);
    ExperienciaTrabalho create(ExperienciaTrabalho experienciaTrabalho, Long id);
    ExperienciaTrabalho update(ExperienciaTrabalho experienciaTrabalho, Long userId, Long expId);
}
