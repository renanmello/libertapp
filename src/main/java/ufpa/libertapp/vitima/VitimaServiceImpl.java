package ufpa.libertapp.vitima;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VitimaServiceImpl implements VitimaService{

    private final VitimaRepository vitimaRepository;

    @Autowired
    public VitimaServiceImpl(VitimaRepository vitimaRepository) {
        this.vitimaRepository = vitimaRepository;
    }

    @Override
    public Vitima viewDados(String cpf) {
       return vitimaRepository.findById(cpf).orElseThrow(() -> new RuntimeException("Vitima não encontrada"));
    }

    @Override
    public Vitima create(Vitima vitima) {

        if (vitimaRepository.existsById(vitima.getCpf())) {
            throw new RuntimeException("CPF já cadastrado.");
        }
        return vitimaRepository.save(vitima);
    }

    @Override
    public Vitima update(Vitima vitima) {
        if(vitima.getCpf() == null){
            throw new RuntimeException("To update a record, you must have an CPF");
        }
        return vitimaRepository.save(vitima);
    }

    @Override
    public void delete(String cpf) {
        vitimaRepository.deleteById(cpf);
    }
}
