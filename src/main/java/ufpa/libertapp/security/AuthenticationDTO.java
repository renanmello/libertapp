package ufpa.libertapp.security;

/**
 * Data Transfer Object (DTO) para representar as credenciais de autenticação de um usuário.
 * <p>
 * Esta classe encapsula o login e a senha necessários para autenticar um usuário.
 * </p>
 *
 * @param login    o nome de login do usuário
 * @param password a senha do usuário
 * @version 2.0
 * @since 2024
 */
public record AuthenticationDTO(String login, String password) {
}
