package ufpa.libertapp.security;

import ufpa.libertapp.user.UserRole;

/**
 * Data Transfer Object (DTO) para representar os dados de registro de um novo usuário.
 * <p>
 * Esta classe encapsula as informações necessárias para registrar um usuário, incluindo
 * o login, a senha e o papel (role) do usuário.
 * </p>
 *
 * @param login    o nome de login do usuário
 * @param password a senha do usuário
 * @param role     o papel do usuário, representado por {@link UserRole}
 * @version 2.0
 * @since 2024
 */
public record RegisterDTO(String login, String password, UserRole role) {
}
