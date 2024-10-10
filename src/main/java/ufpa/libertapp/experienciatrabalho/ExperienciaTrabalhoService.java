package ufpa.libertapp.experienciatrabalho;

import ufpa.libertapp.vitima.Vitima;

import java.util.Optional;

public interface ExperienciaTrabalhoService {
    ExperienciaTrabalho view(Long id);
    ExperienciaTrabalho create(ExperienciaTrabalho experienciaTrabalho, Long id);
}
