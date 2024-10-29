package ufpa.libertapp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ufpa.libertapp.user.UserRepository;

import java.io.IOException;


@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);
    /*
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);

        if (token != null) {
            try {
                String login = tokenService.validateToken(token); // Valida o token e obtém o login
                if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(login);

                    if (userDetails != null) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (RuntimeException e) {
                // Token inválido ou expirado, logue o erro se necessário e continue sem autenticar
                System.out.println("Erro ao validar o token: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

     */


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Ignora requisições para os endpoints do Swagger UI e documentação OpenAPI
        String requestURI = request.getRequestURI();
        logger.info("Processando requisição URI: {}", requestURI);
        if (requestURI.startsWith("/swagger-ui") || requestURI.startsWith("/v3/api-docs") || requestURI.equals("/favicon.ico")) {
            logger.info("Ignorando autenticação para URI: {}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        var token = this.recoverToken(request);


        if (token != null) {
            logger.info("Token encontrado, validando: {}", token);
            var login = tokenService.validateToken(token);

            UserDetails user = userRepository.findByLogin(login);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("Autenticação bem-sucedida para o usuário: {}", login);
        }else {
            logger.warn("Token inválido ou expirado.");
        }
        filterChain.doFilter(request, response);
    }


    //first way to recover token
    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring(7); // Remove "Bearer " para obter o token puro
    }


    //second way to recover token
    /*
    public String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
     */

}

