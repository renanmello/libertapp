package ufpa.libertapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Repositório para operações de banco de dados relacionadas à entidade {@link User}.
 * <p>
 * Esta interface herda métodos padrão do JpaRepository para manipulação de dados de usuário
 * e inclui uma consulta personalizada para buscar usuários por login.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Busca um usuário pelo login fornecido.
     *
     * @param login o login do usuário a ser encontrado
     * @return o objeto {@link UserDetails} do usuário correspondente
     */
    UserDetails findByLogin(String login);

    /**
     * Salva um usuário no banco de dados.
     *
     * @param user o objeto {@link User} a ser salvo
     * @return o objeto {@link User} recém-salvo
     */
    User save(User user);


}
