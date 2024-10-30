package ufpa.libertapp.experienciatrabalho;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufpa.libertapp.vitima.Vitima;
import ufpa.libertapp.vitima.VitimaRepository;

import java.util.List;

/**
 * Implementação do serviço para gerenciamento de experiências de trabalho, incluindo operações
 * de visualização, criação e atualização de experiências associadas a um usuário específico.
 *
 * @version 2.0
 * @since 2024
 */
@Service
@RequiredArgsConstructor
public class ExperienciaTrabalhoServiceImpl implements ExperienciaTrabalhoService {

    @Autowired
    private final VitimaRepository vitimaRepository;
    @Autowired
    private final ExperienciaTrabalhoRepository experienciaTrabalhoRepository;

    /**
     * Recupera todas as experiências de trabalho associadas ao usuário com o ID especificado.
     *
     * @param id o ID do usuário cujas experiências de trabalho serão recuperadas
     * @return uma lista de objetos {@link ExperienciaTrabalho} associados ao usuário
     */
    @Override
    public List<ExperienciaTrabalho> view(Long id) {
        return experienciaTrabalhoRepository.findByUserId(id);
    }

    /**
     * Cria uma nova experiência de trabalho associada a um usuário.
     *
     * @param experienciaTrabalho o objeto {@link ExperienciaTrabalho} a ser criado
     * @param id                  o ID do usuário ao qual a experiência de trabalho será associada
     * @return a experiência de trabalho recém-criada e salva no repositório
     */
    @Override
    public ExperienciaTrabalho create(ExperienciaTrabalho experienciaTrabalho, Long id) {

        Vitima vitima = vitimaRepository.findByUserId(id);
        experienciaTrabalho.setVitima(vitima);
        return experienciaTrabalhoRepository.save(experienciaTrabalho);


    }

    /**
     * Atualiza uma experiência de trabalho existente de um usuário.
     *
     * @param experienciaTrabalho o objeto {@link ExperienciaTrabalho} com os dados atualizados
     * @param userId              o ID do usuário que possui a experiência de trabalho
     * @param expId               o ID da experiência de trabalho a ser atualizada
     * @return a experiência de trabalho atualizada e salva no repositório
     * @throws RuntimeException se a experiência de trabalho não for encontrada
     */
    @Override
    public ExperienciaTrabalho update(ExperienciaTrabalho experienciaTrabalho, Long userId, Long expId) {
        Vitima vitima = vitimaRepository.findByUserId(userId);
        ExperienciaTrabalho edit_exp = experienciaTrabalhoRepository.findById(expId).orElseThrow(() -> new RuntimeException("Experiencia nao cadastrada"));

        edit_exp.setVitima(vitima);
        edit_exp.setCargo(experienciaTrabalho.getCargo());
        edit_exp.setNomeDaEmpresa(experienciaTrabalho.getNomeDaEmpresa());
        edit_exp.setDataFim(experienciaTrabalho.getDataFim());
        edit_exp.setDataInicio(experienciaTrabalho.getDataInicio());

        return experienciaTrabalhoRepository.save(edit_exp);
    }
}
