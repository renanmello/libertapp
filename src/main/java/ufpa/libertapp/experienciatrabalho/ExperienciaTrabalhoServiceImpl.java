package ufpa.libertapp.experienciatrabalho;

import org.springframework.stereotype.Service;
import ufpa.libertapp.vitima.Vitima;
import ufpa.libertapp.vitima.VitimaRepository;

import java.util.Optional;

@Service
public class ExperienciaTrabalhoServiceImpl implements ExperienciaTrabalhoService{

    VitimaRepository vitimaRepository;
    ExperienciaTrabalhoRepository experienciaTrabalhoRepository;

    @Override
    public Optional<ExperienciaTrabalho> view(Long id) {
        return experienciaTrabalhoRepository.findById(id);
    }

    @Override
    public ExperienciaTrabalho create(ExperienciaTrabalho experienciaTrabalho, Vitima vitima) {
        experienciaTrabalho.setVitima(vitima);
        return experienciaTrabalhoRepository.save(experienciaTrabalho);
    }
}
