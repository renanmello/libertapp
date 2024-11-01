package ufpa.libertapp.passwordresettoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço para limpeza periódica de tokens de redefinição de senha expirados.
 * <p>
 * Esta classe identifica tokens de redefinição de senha expirados no banco de dados
 * e os remove automaticamente a cada intervalo de 1 hora.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Service
public class TokenCleanupService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    /**
     * Limpa todos os tokens de redefinição de senha expirados do banco de dados.
     * <p>
     * Este método é executado automaticamente a cada 1 hora, verificando e removendo tokens
     * cuja data de expiração já passou.
     * </p>
     */
    @Scheduled(fixedRate = 3600000) // Executa a cada 1 hora
    public void cleanExpiredTokens() {
        List<PasswordResetToken> expiredTokens = passwordResetTokenRepository.findAll()
                .stream()
                .filter(token -> token.getExpirationTime().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
        passwordResetTokenRepository.deleteAll(expiredTokens);
    }
}

