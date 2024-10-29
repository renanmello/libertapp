package ufpa.libertapp.orgao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufpa.libertapp.user.User;
import ufpa.libertapp.user.UserRepository;

import java.util.Optional;

@Service
public class OrgaoServiceImpl implements OrgaoService {

    private final OrgaoRepository orgaoRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrgaoServiceImpl(OrgaoRepository orgaoRepository, UserRepository userRepository) {
        this.orgaoRepository = orgaoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Orgao view(Long id) {
        return orgaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Órgão com ID " + id + " não encontrado."));
    }

    @Override
    public Orgao create(Orgao orgao, Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new RuntimeException("Usuário com ID " + userId + " não encontrado.");
        }

        orgao.setUser(user.get());
        return orgaoRepository.save(orgao);
    }

    @Override
    public Orgao update(Orgao orgao, Long id) {
        Orgao existingOrgao = orgaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Órgão com ID " + id + " não encontrado."));

        existingOrgao.setNome(orgao.getNome());
        // Atualiza outros campos conforme necessário

        return orgaoRepository.save(existingOrgao);
    }

    @Override
    public void delete(Long id) {
        if (!orgaoRepository.existsById(id)) {
            throw new RuntimeException("Órgão com ID " + id + " não encontrado.");
        }
        orgaoRepository.deleteById(id);
    }
}