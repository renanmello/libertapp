package ufpa.libertapp.vitima;


import ufpa.libertapp.curso.CursoDTO;
import ufpa.libertapp.experienciatrabalho.ExperienciaDTO;

import java.util.List;

/**
 * Serviço para operações relacionadas à entidade {@link Vitima}.
 * <p>
 * Define métodos para visualizar, criar, atualizar e excluir dados de vítimas, além de buscar
 * detalhes específicos e informações associadas, como cursos e experiências de trabalho.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
public interface VitimaService {
    /**
     * Recupera os dados de uma vítima específica pelo ID do usuário associado.
     *
     * @param id o ID do usuário
     * @return o objeto {@link Vitima} correspondente
     */
    Vitima viewDados(Long id);

    /**
     * Cria um novo registro de vítima associado a um usuário específico.
     *
     * @param vitima  o objeto {@link Vitima} contendo os dados da vítima
     * @param user_id o ID do usuário ao qual a vítima será associada
     * @return o objeto {@link Vitima} recém-criado e salvo
     */
    Vitima create(Vitima vitima, Long user_id);

    /**
     * Atualiza os dados de uma vítima existente.
     *
     * @param vitima o objeto {@link Vitima} com os dados atualizados
     * @param id     o ID da vítima a ser atualizada
     * @return o objeto {@link Vitima} atualizado
     */
    Vitima update(Vitima vitima, Long id);

    /**
     * Exclui uma vítima pelo CPF.
     *
     * @param cpf o CPF da vítima a ser excluída
     */
    void delete(String cpf);

    /**
     * Recupera uma lista de todas as vítimas cadastradas.
     *
     * @return uma lista de objetos {@link Vitima}
     */
    List<Vitima> viewAll();

    /**
     * Recupera uma lista detalhada de vítimas, incluindo informações essenciais.
     *
     * @return uma lista de {@link VitimaDTO} com detalhes específicos de cada vítima
     */
    List<VitimaDTO> findAllVitimaDetails();

    /**
     * Recupera uma lista de cursos associados a um usuário específico pelo ID.
     *
     * @param userId o ID do usuário
     * @return uma lista de {@link CursoDTO} representando os cursos do usuário
     */
    List<CursoDTO> findCursosByUserId(Long userId);

    /**
     * Recupera uma lista de experiências de trabalho associadas a um usuário específico pelo ID.
     *
     * @param userId o ID do usuário
     * @return uma lista de {@link ExperienciaDTO} representando as experiências de trabalho do usuário
     */
    List<ExperienciaDTO> findExperienciasByUserId(Long userId);
}
