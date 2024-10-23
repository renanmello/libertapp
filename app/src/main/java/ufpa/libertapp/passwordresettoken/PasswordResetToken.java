package ufpa.libertapp.passwordresettoken;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpa.libertapp.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name="password_reset_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String token;

    @Column(name = "expiration_time", nullable = false)
    private LocalDateTime expirationTime;

    @Column(name = "temp_password", nullable = true)
    private String temp_password;
}
