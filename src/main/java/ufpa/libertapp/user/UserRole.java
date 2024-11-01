package ufpa.libertapp.user;

import lombok.Getter;

/**
 * Enumeração que representa os diferentes papéis (roles) dos usuários no sistema.
 * <p>
 * Cada papel define um nível específico de acesso e funcionalidades disponíveis para o usuário.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Getter
public enum UserRole {
    /**
     * Papel de administrador, com permissões completas no sistema.
     */
    ADMIN("admin"),
    /**
     * Papel de vítima, com acesso limitado a funcionalidades específicas.
     */
    VITIMA("vitima"),
    /**
     * Papel de empresa, com permissões para gerenciar recursos de empresa.
     */
    EMPRESA("empresa"),
    /**
     * Papel de órgão, com permissões específicas para gerenciar operações governamentais ou regulatórias.
     */
    ORGAO("orgao");
    /**
     * Representação textual do papel do usuário.
     */
    private String role;

    /**
     * Construtor para associar uma string a cada papel.
     *
     * @param role o texto que representa o papel
     */
    UserRole(String role) {
        this.role = role;
    }

}
