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

@RestController
@RequestMapping("/api")
public class PasswordResetTokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @PostMapping("/request-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String login) {
        User user = (User) userRepository.findByLogin(login);
        PasswordResetToken before = passwordResetTokenRepository.findByUserLogin(login).orElseThrow(()-> new RuntimeException("token nao encontrado"));
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
