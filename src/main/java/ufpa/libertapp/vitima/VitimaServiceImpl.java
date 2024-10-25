package ufpa.libertapp.vitima;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufpa.libertapp.curso.CursoDTO;
import ufpa.libertapp.experienciatrabalho.ExperienciaDTO;
import ufpa.libertapp.user.User;
import ufpa.libertapp.user.UserRepository;
import java.util.List;

@Service
public class VitimaServiceImpl implements VitimaService {
    private final VitimaRepository vitimaRepository;
    private final UserRepository userRepository;

    @Autowired
    public VitimaServiceImpl(VitimaRepository vitimaRepository, UserRepository userRepository) {
        this.vitimaRepository = vitimaRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Vitima viewDados(Long id) {
        return vitimaRepository.findByUserId(id);
    }

    @Override
    public Vitima create(Vitima vitima, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()
            -> new RuntimeException("Usuário não encontrado."));

        vitima.setUser(user);
        if (vitimaRepository.existsById(vitima.getCpf())) {
            throw new RuntimeException("CPF já cadastrado.");
        }

        vitima.setEmail(user.getUsername());
        return vitimaRepository.save(vitima);
    }

    @Override
    public Vitima update(Vitima vitima, Long id) {
        Vitima existingVitima = vitimaRepository.findByUserId(id);
        User user = userRepository.findById(id).orElseThrow(()
            -> new RuntimeException("Usuário não encontrado."));

        // Atualiza os campos necessários
        existingVitima.setNome(vitima.getNome());
        existingVitima.setCep(vitima.getCep());
        existingVitima.setCidade(vitima.getCidade());
        existingVitima.setEmail(vitima.getEmail());
        existingVitima.setEndereco(vitima.getEndereco());
        existingVitima.setTelefone(vitima.getTelefone());
        existingVitima.setRg(vitima.getRg());
        existingVitima.setHorario(vitima.getHorario());
        existingVitima.setEstado(vitima.getEstado());
        existingVitima.setPcd(vitima.getPcd());
        existingVitima.setConfirmacao_termo(vitima.getConfirmacao_termo());
        existingVitima.setCurriculoPdf(null);
        existingVitima.setData_nascimento(vitima.getData_nascimento());
        existingVitima.setUser(user);
        existingVitima.setContactada(true);
        existingVitima.setEscolaridade(vitima.getEscolaridade());
        //if(vitima.getCpf() == null){
        //    throw new RuntimeException("To update a record, you must have an CPF");
        //}

        // Atualiza o usuário, se necessário
        //if (vitima.getUser() != null) {
        //    User user = userRepository.findById(vitima.getUser().getId())
        //            .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        //    existingVitima.setUser(user);
        //}


        return vitimaRepository.save(existingVitima);
    }

    @Override
    public void delete(String cpf) {
        vitimaRepository.deleteById(cpf);
    }

    @Override
    public List<Vitima> viewAll() {
        return vitimaRepository.findAll();
    }

    @Override
    public List<VitimaDTO> findAllVitimaDetails() {
        return vitimaRepository.findAllVitimaDetails();
    }

    @Override
    public List<CursoDTO> findCursosByUserId(Long userId) {
        return vitimaRepository.findCursosByUserId(userId);
    }

    @Override
    public List<ExperienciaDTO> findExperienciasByUserId(Long userId) {
        return vitimaRepository.findExperienciasByUserId(userId);
    }
}
