package ufpa.libertapp.passwordresettoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenCleanupService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Scheduled(fixedRate = 3600000) // Executa a cada 1 hora
    public void cleanExpiredTokens() {
        List<PasswordResetToken> expiredTokens = passwordResetTokenRepository.findAll()
                .stream()
                .filter(token -> token.getExpirationTime().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
        passwordResetTokenRepository.deleteAll(expiredTokens);
    }
}

