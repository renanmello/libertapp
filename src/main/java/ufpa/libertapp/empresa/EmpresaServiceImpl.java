package ufpa.libertapp.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufpa.libertapp.orgao.Orgao;
import ufpa.libertapp.orgao.OrgaoRepository;
import ufpa.libertapp.user.User;
import ufpa.libertapp.user.UserRepository;

import java.util.Optional;

/**
 * Implementação do serviço {@link EmpresaService} para operações relacionadas à entidade {@link Empresa}.
 * <p>
 * Esta classe fornece a lógica de negócios para visualizar, criar, atualizar e excluir empresas, além
 * de gerenciar relacionamentos com as entidades {@link User} e {@link Orgao}.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final UserRepository userRepository;
    private final OrgaoRepository orgaoRepository;

    /**
     * Construtor para injeção de dependências dos repositórios.
     *
     * @param empresaRepository repositório de empresa
     * @param userRepository    repositório de usuário
     * @param orgaoRepository   repositório de órgão
     */
    @Autowired
    public EmpresaServiceImpl(EmpresaRepository empresaRepository, UserRepository userRepository, OrgaoRepository orgaoRepository) {
        this.empresaRepository = empresaRepository;
        this.userRepository = userRepository;
        this.orgaoRepository = orgaoRepository;
    }

    /**
     * Recupera uma empresa específica pelo ID.
     *
     * @param id o ID da empresa a ser visualizada
     * @return o objeto {@link Empresa} correspondente ao ID fornecido
     * @throws RuntimeException se a empresa não for encontrada
     */
    @Override
    public Empresa view(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa com ID " + id + " não encontrada."));
    }

    /**
     * Cria uma nova empresa associada a um usuário e a um órgão específicos.
     *
     * @param empresa o objeto {@link Empresa} contendo os dados da nova empresa
     * @param userId  o ID do usuário associado
     * @param orgaoId o ID do órgão associado
     * @return a entidade {@link Empresa} criada e salva
     * @throws RuntimeException se o usuário ou o órgão não forem encontrados
     */
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
        Empresa new_emp = new Empresa();
        new_emp.setUser(user.get());
        new_emp.setOrgao(orgao.get());
        new_emp.setNome(empresa.getNome());
        new_emp.setRazao_social(empresa.getRazao_social());
        new_emp.setCnpj(empresa.getCnpj());

        return empresaRepository.save(new_emp);
    }

    /**
     * Atualiza os dados de uma empresa existente.
     *
     * @param empresa o objeto {@link Empresa} contendo os dados atualizados
     * @param id      o ID da empresa a ser atualizada
     * @return a entidade {@link Empresa} atualizada
     * @throws RuntimeException se a empresa não for encontrada
     */
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

    /**
     * Exclui uma empresa específica pelo ID.
     *
     * @param id o ID da empresa a ser excluída
     * @throws RuntimeException se a empresa não for encontrada
     */
    @Override
    public void delete(Long id) {
        if (!empresaRepository.existsById(id)) {
            throw new RuntimeException("Empresa com ID " + id + " não encontrada.");
        }
        empresaRepository.deleteById(id);
    }
}