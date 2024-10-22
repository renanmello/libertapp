package ufpa.libertapp.passwordresettoken;

import org.springframework.data.jpa.repository.JpaRepository;
import ufpa.libertapp.user.User;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);

    void deleteByUser(User user);

    void deleteByToken(String token);

    void deleteByTemp_password(String temp_password);
}
