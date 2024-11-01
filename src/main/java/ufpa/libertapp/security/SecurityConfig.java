package ufpa.libertapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Configura as definições de segurança para o sistema, incluindo controle de acesso,
 * gerenciamento de sessão, configuração de CORS e codificação de senha.
 * <p>
 * A classe utiliza o filtro {@link SecurityFilter} para validação do token JWT e
 * gerencia as permissões de acesso aos endpoints da API.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * Filtro de segurança personalizado para validação de autenticação baseada em JWT.
     */
    @Autowired
    SecurityFilter securityFilter;
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    /**
     * Configura o {@link SecurityFilterChain} para definir as permissões de acesso,
     * as políticas de sessão e os endpoints públicos.
     *
     * @param httpSecurity configurações de segurança HTTP
     * @return configuração de segurança completa para o aplicativo
     * @throws Exception se houver falhas ao configurar a segurança
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        logger.info("Configuração de segurança inicializada");
        return httpSecurity.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> {
                logger.info("Permitindo acesso aos endpoints do Swagger");
                // Configura endpoints públicos
                authorize
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                    .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()
                    .requestMatchers("/favicon.ico").permitAll()
                    // Configura permissões específicas para endpoints da aplicação
                    .requestMatchers(HttpMethod.POST, "/vitima").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/vitima").hasAnyRole("ADMIN", "VITIMA")
                    .requestMatchers(HttpMethod.GET, "/vitima").hasAnyRole("ADMIN", "VITIMA")
                    .requestMatchers(HttpMethod.GET, "/vitima/all").hasAnyRole("ADMIN", "EMPRESA")
                    .requestMatchers(HttpMethod.POST, "/curso/create").hasAnyRole("ADMIN", "VITIMA")
                    .requestMatchers(HttpMethod.GET, "/curso").hasAnyRole("ADMIN", "VITIMA")
                    .requestMatchers(HttpMethod.PUT, "/curso").hasAnyRole("ADMIN", "VITIMA")
                    .requestMatchers(HttpMethod.DELETE, "/curso").hasAnyRole("ADMIN", "VITIMA")
                    .requestMatchers(HttpMethod.POST, "/experiencia/create").hasAnyRole("VITIMA", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/experiencia").hasAnyRole("VITIMA", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/experiencia").hasAnyRole("VITIMA", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/experiencia").hasAnyRole("VITIMA", "ADMIN")
                    .requestMatchers(HttpMethod.POST, "/orgao").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/orgao/empresa").hasRole("ORGAO")
                    .requestMatchers(HttpMethod.GET, "/orgao/empresa").hasRole("ORGAO")
                    .requestMatchers(HttpMethod.PUT, "/orgao/empresa").hasRole("ORGAO")
                    .requestMatchers(HttpMethod.DELETE, "/orgao/empresa").hasRole("ORGAO")
                    .requestMatchers(HttpMethod.GET, "/orgao").hasRole("ORGAO")
                    .requestMatchers(HttpMethod.GET, "/empresa").hasRole("EMPRESA")
                    .requestMatchers(HttpMethod.POST, "/empresa").hasRole("EMPRESA")
                    .requestMatchers(HttpMethod.PUT, "/empresa").hasRole("EMPRESA")
                    .requestMatchers(HttpMethod.DELETE, "/empresa").hasRole("EMPRESA")
                    .anyRequest().authenticated();// Outros endpoints requerem autenticação
            })
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    /**
     * Configuração de CORS para permitir requisições entre domínios.
     *
     * @return fonte de configuração de CORS aplicada a todos os endpoints da API
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8080",
            "http://localhost:8080/swagger-ui.html", "http://localhost:8080/favicon.ico")); // // Permitir o front-end e o Swagger UI
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos permitidos
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Cabeçalhos permitidos
        configuration.setAllowCredentials(true); // Permitir credenciais (cookies, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplicar para todos os endpoints

        return source;
    }

    /**
     * Configura o gerenciador de autenticação para o Spring Security.
     *
     * @param authenticationConfiguration configuração de autenticação
     * @return gerenciador de autenticação
     * @throws Exception se ocorrer um erro ao obter o gerenciador de autenticação
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean para o codificador de senha usando o BCrypt.
     *
     * @return instância de {@link BCryptPasswordEncoder} para codificação de senhas
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
