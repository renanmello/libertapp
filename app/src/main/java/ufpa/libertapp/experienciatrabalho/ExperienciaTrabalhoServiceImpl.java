package ufpa.libertapp.experienciatrabalho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ufpa.libertapp.vitima.Vitima;
import ufpa.libertapp.vitima.VitimaRepository;

import java.util.Optional;

@Service
public class ExperienciaTrabalhoServiceImpl implements ExperienciaTrabalhoService {

    private final VitimaRepository vitimaRepository;
    private final ExperienciaTrabalhoRepository experienciaTrabalhoRepository;

    @Autowired
    public ExperienciaTrabalhoServiceImpl(VitimaRepository vitimaRepository, ExperienciaTrabalhoRepository experienciaTrabalhoRepository) {
        this.experienciaTrabalhoRepository = experienciaTrabalhoRepository;
        this.vitimaRepository = vitimaRepository;
    }

    @Override
    public Optional<ExperienciaTrabalho> view(Long id) {
        return experienciaTrabalhoRepository.findById(id);
    }

    @Override
    public ExperienciaTrabalho create(@RequestBody ExperienciaTrabalho experienciaTrabalho, String cpf) {

        Vitima vitima = vitimaRepository.findById(cpf).orElseThrow(() -> new RuntimeException("Cpf invalido."));
        experienciaTrabalho.setVitima(vitima);
        return experienciaTrabalhoRepository.save(experienciaTrabalho);


    }
}
