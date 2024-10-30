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

/**
 * Serviço para geração e validação de tokens JWT, utilizado para autenticação
 * de usuários e gerenciamento de tokens de redefinição de senha.
 *
 * @version 2.0
 * @since 2024
 */
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Gera um token JWT para autenticação do usuário.
     *
     * @param user o usuário para o qual o token será gerado
     * @return o token JWT como uma string
     */
    public String generateToken(User user) {
        return generateToken(user.getLogin(), 2); // Expira em 2 horas
    }

    /**
     * Gera um token de redefinição de senha para o usuário.
     *
     * @param user o usuário para o qual o token será gerado
     * @return o token JWT de redefinição de senha
     */
    public String generatePasswordResetToken(User user) {
        return generateToken(user.getLogin(), 730); // Expira em 1 mes
    }

    /**
     * Gera um token de recuperação de senha para o usuário.
     *
     * @param user o usuário para o qual o token será gerado
     * @return o token JWT de recuperação de senha
     */
    public String generatePasswordForgotToken(User user) {
        return generateToken(user.getLogin(), 1); // Expira em 1 hora
    }

    /**
     * Gera um token JWT com um assunto e tempo de expiração especificados.
     *
     * @param subject       o assunto (subject) do token, geralmente o login do usuário
     * @param hoursToExpire o número de horas até o token expirar
     * @return o token JWT gerado
     * @throws RuntimeException se houver um erro na geração do token
     */
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

    /**
     * Gera a data de expiração do token JWT com base nas horas especificadas.
     *
     * @param hours o número de horas até o token expirar
     * @return a data de expiração como um objeto {@link Instant}
     */
    private Instant genExpirationDate(int hours) {

        return LocalDateTime.now().plusHours(hours).toInstant(ZoneOffset.of("-03:00"));
    }

    /**
     * Valida um token JWT e retorna o assunto (subject) associado.
     *
     * @param token o token JWT a ser validado
     * @return o assunto (subject) do token se for válido
     * @throws RuntimeException se o token for inválido ou expirado
     */
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("libertapp")
                    .build()
                    .verify(token)
                    .getSubject()
                    ;
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido ou expirado", e);
        }
    }

    /**
     * Valida o token de redefinição de senha e verifica se pertence ao usuário.
     *
     * @param token     o token JWT a ser validado
     * @param userLogin o login do usuário associado ao token
     * @return true se o token for válido e pertence ao usuário, caso contrário, false
     */
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

    /**
     * Valida o token de recuperação de senha e verifica se pertence ao usuário.
     *
     * @param token     o token JWT a ser validado
     * @param userLogin o login do usuário associado ao token
     * @return true se o token for válido e pertence ao usuário, caso contrário, false
     */
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
