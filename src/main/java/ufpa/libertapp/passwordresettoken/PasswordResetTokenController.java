package ufpa.libertapp.passwordresettoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ufpa.libertapp.user.User;
import ufpa.libertapp.user.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PasswordResetTokenController {

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String login) {
        User user = (User)userRepository.findByLogin(login);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Gera e salva o token no banco de dados
        PasswordResetToken token = passwordResetTokenService.createPasswordResetToken(user);
        // Aqui você envia o email com o token gerado
        String resetLink = "http://seu-site.com/reset-password?token=" + token.getToken();
        System.out.println("Link de redefinição: " + resetLink);

        return ResponseEntity.ok("Password reset link sent");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        Optional<PasswordResetToken> resetTokenOpt = passwordResetTokenService.validatePasswordResetToken(token);
        if (!resetTokenOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }

        PasswordResetToken resetToken = resetTokenOpt.get();
        User user = resetToken.getUser();
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);

        // Apaga o token depois de usá-lo
        passwordResetTokenService.deletePasswordResetToken(token);

        return ResponseEntity.ok("Password successfully reset");
    }
}
