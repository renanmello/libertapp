package ufpa.libertapp.vitima;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufpa.libertapp.curso.CursoDTO;
import ufpa.libertapp.experienciatrabalho.ExperienciaDTO;
import ufpa.libertapp.user.User;
import ufpa.libertapp.user.UserRepository;


import java.util.List;

/**
 * Implementação do serviço de gerenciamento de vítimas, responsável por operações
 * de criação, visualização, atualização e exclusão de vítimas, bem como pela
 * recuperação de cursos e experiências de trabalho associados.
 *
 * @version 2.0
 * @since 2024
 */
@Service
public class VitimaServiceImpl implements VitimaService {

    private final VitimaRepository vitimaRepository;
    private final UserRepository userRepository;

    /**
     * Construtor que injeta dependências de repositórios para o serviço.
     *
     * @param vitimaRepository o repositório para operações de vítimas
     * @param userRepository   o repositório para operações de usuários
     */
    @Autowired
    public VitimaServiceImpl(VitimaRepository vitimaRepository, UserRepository userRepository) {
        this.vitimaRepository = vitimaRepository;
        this.userRepository = userRepository;
    }

    /**
     * Recupera os dados de uma vítima específica pelo ID do usuário.
     *
     * @param id o ID do usuário associado à vítima
     * @return o objeto {@link Vitima} correspondente
     */
    @Override
    public Vitima viewDados(Long id) {
        return vitimaRepository.findByUserId(id);
    }

    /**
     * Cria um novo registro de vítima associado a um usuário.
     *
     * @param vitima o objeto {@link Vitima} com os dados da vítima
     * @param userId o ID do usuário associado à vítima
     * @return o objeto {@link Vitima} recém-criado e salvo no repositório
     * @throws RuntimeException se o CPF já estiver cadastrado ou se o usuário não for encontrado
     */
    @Override
    public Vitima create(Vitima vitima, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        vitima.setUser(user);
        if (vitimaRepository.existsById(vitima.getCpf())) {
            throw new RuntimeException("CPF já cadastrado.");
        }
        vitima.setEmail(user.getUsername());
        return vitimaRepository.save(vitima);
    }

    /**
     * Atualiza os dados de uma vítima existente associada a um usuário.
     *
     * @param vitima o objeto {@link Vitima} com os dados atualizados da vítima
     * @param id     o ID do usuário associado à vítima
     * @return o objeto {@link Vitima} atualizado e salvo no repositório
     * @throws RuntimeException se o usuário não for encontrado
     */
    @Override
    public Vitima update(Vitima vitima, Long id) {

        Vitima existingVitima = vitimaRepository.findByUserId(id);
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        /*
        Vitima = vitimaRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Vítima não encontrada com o CPF: " + cpf));
        User user = userRepository.findById(vitima.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        */


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

    /**
     * Exclui o registro de uma vítima pelo CPF.
     *
     * @param cpf o CPF da vítima a ser excluída
     */
    @Override
    public void delete(String cpf) {
        vitimaRepository.deleteById(cpf);
    }

    /**
     * Recupera todos os registros de vítimas.
     *
     * @return uma lista de objetos {@link Vitima}
     */
    @Override
    public List<Vitima> viewAll() {
        return vitimaRepository.findAll();
    }

    /**
     * Recupera detalhes de todas as vítimas no formato {@link VitimaDTO}.
     *
     * @return uma lista de {@link VitimaDTO} com detalhes das vítimas
     */
    @Override
    public List<VitimaDTO> findAllVitimaDetails() {
        return vitimaRepository.findAllVitimaDetails();
    }

    /**
     * Recupera todos os cursos associados a um usuário específico.
     *
     * @param userId o ID do usuário
     * @return uma lista de {@link CursoDTO} representando os cursos do usuário
     */
    @Override
    public List<CursoDTO> findCursosByUserId(Long userId) {
        return vitimaRepository.findCursosByUserId(userId);
    }

    /**
     * Recupera todas as experiências de trabalho associadas a um usuário específico.
     *
     * @param userId o ID do usuário
     * @return uma lista de {@link ExperienciaDTO} representando as experiências de trabalho do usuário
     */
    @Override
    public List<ExperienciaDTO> findExperienciasByUserId(Long userId) {
        return vitimaRepository.findExperienciasByUserId(userId);
    }


}
