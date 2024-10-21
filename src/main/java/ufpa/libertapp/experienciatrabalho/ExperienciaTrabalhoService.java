package ufpa.libertapp.experienciatrabalho;

import java.util.List;

public interface ExperienciaTrabalhoService {
    List<ExperienciaTrabalho> view(Long id);
    ExperienciaTrabalho create(ExperienciaTrabalho experienciaTrabalho, Long id);
    ExperienciaTrabalho update(ExperienciaTrabalho experienciaTrabalho, Long userId, Long expId);
}
