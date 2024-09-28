package ufpa.libertapp.experienciatrabalho;

import java.util.Optional;

public interface ExperienciaTrabalhoService {
    Optional<ExperienciaTrabalho> view(Long id);
    ExperienciaTrabalho create(ExperienciaTrabalho experienciaTrabalho, String cpf);
}
