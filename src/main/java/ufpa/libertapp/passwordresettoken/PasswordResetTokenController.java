package ufpa.libertapp.passwordresettoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ufpa.libertapp.security.TokenService;
import ufpa.libertapp.user.User;
import ufpa.libertapp.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Controlador REST para operações de redefinição de senha de usuário.
 * <p>
 * Fornece endpoints para solicitar e redefinir a senha de um usuário, utilizando tokens de
 * redefinição de senha com data de expiração.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@RestController
@RequestMapping("/api")
public class PasswordResetTokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    /**
     * Endpoint para solicitar um link de redefinição de senha.
     * <p>
     * Gera um token de redefinição de senha para o usuário especificado e imprime o link no console.
     * </p>
     *
     * @param login o login do usuário que solicitou a redefinição de senha
     * @return uma resposta de sucesso se o link de redefinição for enviado, ou uma resposta de erro caso o usuário não seja encontrado
     */
    @PostMapping("/request-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String login) {
        User user = (User) userRepository.findByLogin(login);
        PasswordResetToken before = passwordResetTokenRepository.findByUserLogin(login).orElseThrow(() -> new RuntimeException("token nao encontrado"));
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Gera e salva o token no banco de dados
        String token = tokenService.generatePasswordForgotToken(user);

        // Aqui você envia o email com o token gerado
        String resetLink = "http://localhost:8080/reset-password?token=" + token;
        System.out.println("Link de redefinição: " + resetLink);
        // sendEmail(user.getEmail(), resetLink);

        // Salva o token e a data de expiração no banco
        before.setToken(token);
        before.setUser(user);
        before.setExpirationTime(LocalDateTime.now().plusHours(1));
        before.setTemp_password(before.getTemp_password());
        passwordResetTokenRepository.save(before);


        return ResponseEntity.ok("Password reset link sent");
    }

    /**
     * Endpoint para redefinir a senha usando o token de redefinição.
     * <p>
     * Verifica o token de redefinição e, se válido, redefine a senha do usuário.
     * </p>
     *
     * @param token       o token de redefinição de senha
     * @param newPassword a nova senha para o usuário
     * @return uma resposta de sucesso se a senha for redefinida, ou uma resposta de erro caso o token seja inválido ou expirado
     */
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        Optional<PasswordResetToken> resetTokenOpt = passwordResetTokenRepository.findByToken(token);

        if (resetTokenOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }


        PasswordResetToken resetToken = resetTokenOpt.get();

        if (resetToken.getExpirationTime().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token expirado.");
        }

        User user = resetToken.getUser();
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword)); // Altera a senha
        userRepository.save(user);

        // Invalida o token após o uso

        passwordResetTokenRepository.delete(resetToken);

        return ResponseEntity.ok("Password successfully reset");
    }


}
