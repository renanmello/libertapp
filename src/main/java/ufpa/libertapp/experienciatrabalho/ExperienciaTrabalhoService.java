package ufpa.libertapp.experienciatrabalho;


import java.util.List;

/**
 * Serviço para operações relacionadas à entidade {@link ExperienciaTrabalho}.
 * <p>
 * Define métodos para visualizar, criar e atualizar experiências de trabalho associadas a um usuário específico.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
public interface ExperienciaTrabalhoService {
    /**
     * Recupera todas as experiências de trabalho associadas a um usuário específico.
     *
     * @param id o ID do usuário cujas experiências de trabalho serão visualizadas
     * @return uma lista de objetos {@link ExperienciaTrabalho} associados ao usuário
     */
    List<ExperienciaTrabalho> view(Long id);

    /**
     * Cria uma nova experiência de trabalho associada a um usuário específico.
     *
     * @param experienciaTrabalho o objeto {@link ExperienciaTrabalho} contendo os dados da experiência
     * @param id                  o ID do usuário ao qual a experiência será associada
     * @return o objeto {@link ExperienciaTrabalho} recém-criado e salvo
     */
    ExperienciaTrabalho create(ExperienciaTrabalho experienciaTrabalho, Long id);

    /**
     * Atualiza uma experiência de trabalho existente associada a um usuário específico.
     *
     * @param experienciaTrabalho o objeto {@link ExperienciaTrabalho} contendo os dados atualizados da experiência
     * @param userId              o ID do usuário associado à experiência
     * @param expId               o ID da experiência de trabalho a ser atualizada
     * @return o objeto {@link ExperienciaTrabalho} atualizado
     */
    ExperienciaTrabalho update(ExperienciaTrabalho experienciaTrabalho, Long userId, Long expId);
}
