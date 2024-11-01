package ufpa.libertapp.user;

import java.util.Optional;

/**
 * Serviço para operações relacionadas à entidade {@link User}.
 * <p>
 * Define métodos para criação, visualização e atualização de usuários no sistema.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
public interface UserService {
    /**
     * Cria um novo usuário no sistema.
     *
     * @param user o objeto {@link User} contendo os dados do novo usuário
     * @return o objeto {@link User} recém-criado
     */
    User create(User user);

    /**
     * Recupera os dados de um usuário específico pelo ID.
     *
     * @param id o ID do usuário a ser visualizado
     * @return o objeto {@link User} correspondente ao ID fornecido
     */
    User view(Long id);

    /**
     * Atualiza os dados de um usuário existente.
     *
     * @param user o objeto {@link User} contendo os dados atualizados do usuário
     * @param id   o ID do usuário a ser atualizado
     * @return o objeto {@link User} atualizado
     */
    User update(User user, Long id);

}
