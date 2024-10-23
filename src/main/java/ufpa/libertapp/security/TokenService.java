package ufpa.libertapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ufpa.libertapp.user.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        return generateToken(user.getLogin(), 2); // Expira em 2 horas
    }

    public String generatePasswordResetToken(User user) {
        return generateToken(user.getLogin(), 730); // Expira em 1 mes
    }

    public String generatePasswordForgotToken(User user) {
        return generateToken(user.getLogin(), 1); // Expira em 1 hora
    }

    public String generateToken(String subject, int hoursToExpire) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("libertapp")
                    .withSubject(subject)
                    .withExpiresAt(genExpirationDate(hoursToExpire))
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    private Instant genExpirationDate(int hours) {

        return LocalDateTime.now().plusHours(hours).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("libertapp")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido ou expirado", e);
        }
    }

    // Valida o token de redefinição de senha
    public boolean validatePasswordResetToken(String token, String userLogin) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String tokenSubject = JWT.require(algorithm)
                    .withIssuer("libertapp")
                    .build()
                    .verify(token)
                    .getSubject();

            // Verifica se o token pertence ao usuário e se ainda é válido
            return tokenSubject.equals(userLogin);
        } catch (JWTVerificationException e) {
            return false; // Token inválido ou expirado
        }
    }

    // Valida o token de redefinição de senha
    public boolean validatePasswordForgotToken(String token, String userLogin) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String tokenSubject = JWT.require(algorithm)
                    .withIssuer("libertapp")
                    .build()
                    .verify(token)
                    .getSubject();

            // Verifica se o token pertence ao usuário e se ainda é válido
            return tokenSubject.equals(userLogin);
        } catch (JWTVerificationException e) {
            return false; // Token inválido ou expirado
        }
    }

}
