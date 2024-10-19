package ufpa.libertapp.experienciatrabalho;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufpa.libertapp.vitima.Vitima;
import ufpa.libertapp.vitima.VitimaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienciaTrabalhoServiceImpl implements ExperienciaTrabalhoService {
    @Autowired
    private final VitimaRepository vitimaRepository;
    @Autowired
    private final ExperienciaTrabalhoRepository experienciaTrabalhoRepository;

    @Override
    public List<ExperienciaTrabalho> view(Long id) {
        return experienciaTrabalhoRepository.findByUserId(id);
    }

    @Override
    public ExperienciaTrabalho create(ExperienciaTrabalho experienciaTrabalho, Long id) {
        Vitima vitima = vitimaRepository.findByUserId(id);
        experienciaTrabalho.setVitima(vitima);
        return experienciaTrabalhoRepository.save(experienciaTrabalho);
    }
}
