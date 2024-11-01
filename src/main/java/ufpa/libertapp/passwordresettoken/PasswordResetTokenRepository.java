package ufpa.libertapp.passwordresettoken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

/**
 * Repositório para operações de banco de dados relacionadas à entidade {@link PasswordResetToken}.
 * <p>
 * Esta interface herda métodos padrão do JpaRepository para manipulação de tokens de redefinição de senha
 * e inclui consultas personalizadas para buscar e deletar tokens por valor e por login de usuário.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    /**
     * Busca um token de redefinição de senha específico pelo seu valor.
     *
     * @param token o valor do token de redefinição
     * @return um {@link Optional} contendo o {@link PasswordResetToken}, se encontrado
     */
    Optional<PasswordResetToken> findByToken(String token);

    /**
     * Exclui um token de redefinição de senha específico pelo seu valor.
     *
     * @param token o valor do token de redefinição a ser excluído
     */
    void deleteByToken(String token);

    /**
     * Busca um token de redefinição de senha associado a um login de usuário específico.
     *
     * @param login o login do usuário associado ao token
     * @return um {@link Optional} contendo o {@link PasswordResetToken}, se encontrado
     */
    @Query("SELECT prt FROM PasswordResetToken prt JOIN prt.user u WHERE u.login = :login")
    Optional<PasswordResetToken> findByUserLogin(@Param("login") String login);

}
