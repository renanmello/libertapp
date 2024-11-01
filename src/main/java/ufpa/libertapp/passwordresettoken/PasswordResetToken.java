package ufpa.libertapp.passwordresettoken;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpa.libertapp.user.User;

import java.time.LocalDateTime;

/**
 * Entidade que representa um token de redefinição de senha para um usuário.
 * <p>
 * Esta classe armazena informações sobre o token de redefinição de senha, incluindo o usuário associado,
 * o token, a data de expiração e uma senha temporária opcional.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Entity
@Table(name = "password_reset_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PasswordResetToken {
    /**
     * Identificador único do token de redefinição de senha.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Usuário associado ao token de redefinição de senha.
     */
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;
    /**
     * O token de redefinição de senha.
     */
    @Column(nullable = false)
    private String token;
    /**
     * Data e hora de expiração do token.
     */
    @Column(name = "expiration_time", nullable = false)
    private LocalDateTime expirationTime;
    /**
     * Senha temporária opcional para o usuário, usada para autenticação inicial após a redefinição.
     */
    @Column(name = "temp_password", nullable = true)
    private String temp_password;
}
