package ufpa.libertapp.passwordresettoken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String token);

    void deleteByToken(String token);

    @Query("SELECT prt FROM PasswordResetToken prt JOIN prt.user u WHERE u.login = :login")
    Optional<PasswordResetToken> findByUserLogin(@Param("login") String login);
}
