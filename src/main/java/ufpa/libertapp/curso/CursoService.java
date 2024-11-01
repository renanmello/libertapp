package ufpa.libertapp.curso;

import java.util.List;

/**
 * Serviço para operações relacionadas à entidade {@link Curso}.
 * <p>
 * Define métodos para visualizar, criar e atualizar cursos associados a um usuário específico.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
public interface CursoService {
    /**
     * Recupera todos os cursos associados a um usuário específico.
     *
     * @param userId o ID do usuário cujos cursos serão visualizados
     * @return uma lista de objetos {@link Curso} associados ao usuário
     */
    List<Curso> view(Long userId);

    /**
     * Cria um novo curso associado a um usuário específico.
     *
     * @param curso  o objeto {@link Curso} contendo os dados do curso
     * @param userId o ID do usuário ao qual o curso será associado
     * @return o objeto {@link Curso} recém-criado e salvo
     */
    Curso create(Curso curso, Long userId);

    /**
     * Atualiza um curso existente associado a um usuário específico.
     *
     * @param curso   o objeto {@link Curso} contendo os dados atualizados do curso
     * @param userId  o ID do usuário associado ao curso
     * @param cursoId o ID do curso a ser atualizado
     * @return o objeto {@link Curso} atualizado
     */
    Curso update(Curso curso, Long userId, Long cursoId);
}
