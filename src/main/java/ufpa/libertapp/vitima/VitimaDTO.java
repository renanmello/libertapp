package ufpa.libertapp.vitima;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) para encapsular as informações essenciais de uma vítima.
 * <p>
 * Esta classe contém dados básicos da vítima, incluindo nome, email, telefone, senha temporária
 * e o status de contato, facilitando o transporte de dados.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VitimaDTO {
    /**
     * Nome da vítima.
     */
    private String nome;
    /**
     * Endereço de email da vítima.
     */
    private String email;
    /**
     * Número de telefone da vítima.
     */
    private String telefone;
    /**
     * Senha temporária para a vítima, usada para autenticação inicial.
     */
    private String password;
    /**
     * Indica se a vítima foi contatada (true) ou não (false).
     */
    private boolean contactada;

}
