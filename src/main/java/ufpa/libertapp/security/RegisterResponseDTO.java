package ufpa.libertapp.security;

/**
 * Data Transfer Object (DTO) para representar a resposta de registro de um usuário.
 * <p>
 * Esta classe encapsula os dados retornados após o registro de um novo usuário, incluindo
 * o ID e o login do usuário recém-criado.
 * </p>
 *
 * @param id    o identificador único do usuário registrado
 * @param login o nome de login do usuário registrado
 * @version 2.0
 * @since 2024
 */
public record RegisterResponseDTO(Long id, String login) {
}
