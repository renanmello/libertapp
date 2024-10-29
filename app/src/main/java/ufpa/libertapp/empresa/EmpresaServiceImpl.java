package ufpa.libertapp.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufpa.libertapp.orgao.Orgao;
import ufpa.libertapp.orgao.OrgaoRepository;
import ufpa.libertapp.user.User;
import ufpa.libertapp.user.UserRepository;

import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final UserRepository userRepository;
    private final OrgaoRepository orgaoRepository;

    @Autowired
    public EmpresaServiceImpl(EmpresaRepository empresaRepository, UserRepository userRepository, OrgaoRepository orgaoRepository) {
        this.empresaRepository = empresaRepository;
        this.userRepository = userRepository;
        this.orgaoRepository = orgaoRepository;
    }

    @Override
    public Empresa view(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa com ID " + id + " não encontrada."));
    }

    @Override
    public Empresa create(Empresa empresa, Long userId, Long orgaoId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Orgao> orgao = orgaoRepository.findById(orgaoId);

        if (user.isEmpty()) {
            throw new RuntimeException("Usuário com ID " + userId + " não encontrado.");
        }
        if (orgao.isEmpty()) {
            throw new RuntimeException("Órgão com ID " + orgaoId + " não encontrado.");
        }

        empresa.setUser(user.get());
        empresa.setOrgao(orgao.get());

        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa update(Empresa empresa, Long id) {
        Empresa existingEmpresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa com ID " + id + " não encontrada."));

        existingEmpresa.setNome(empresa.getNome());
        existingEmpresa.setCnpj(empresa.getCnpj());
        existingEmpresa.setRazao_social(empresa.getRazao_social());
        // Verificar e atualizar relacionamento com User e Orgao, se necessário

        return empresaRepository.save(existingEmpresa);
    }

    @Override
    public void delete(Long id) {
        if (!empresaRepository.existsById(id)) {
            throw new RuntimeException("Empresa com ID " + id + " não encontrada.");
        }
        empresaRepository.deleteById(id);
    }
}