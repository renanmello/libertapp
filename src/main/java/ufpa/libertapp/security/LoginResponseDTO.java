package ufpa.libertapp.security;

import java.util.Collection;

/**
 * Data Transfer Object (DTO) para representar a resposta de autenticação de login de um usuário.
 * <p>
 * Esta classe encapsula os dados retornados após a autenticação bem-sucedida, incluindo o token JWT,
 * as roles (permissões) do usuário e o ID do usuário.
 * </p>
 *
 * @param token o token JWT gerado para autenticação do usuário
 * @param role  a coleção de roles (permissões) associadas ao usuário
 * @param id    o identificador único do usuário autenticado
 * @version 2.0
 * @since 2024
 */
public record LoginResponseDTO(String token, Collection role, Long id) {

}
