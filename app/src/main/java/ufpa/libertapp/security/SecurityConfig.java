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

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/vitima").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/vitima").hasAnyRole("ADMIN","VITIMA")
                        .requestMatchers(HttpMethod.GET, "/vitima").hasAnyRole("ADMIN","VITIMA")
                        .requestMatchers(HttpMethod.GET, "/vitima/all").hasAnyRole("ADMIN","EMPRESA")
                        .requestMatchers(HttpMethod.POST, "/curso/create").hasAnyRole("ADMIN","VITIMA")
                        .requestMatchers(HttpMethod.GET, "/curso").hasAnyRole("ADMIN","VITIMA")
                        .requestMatchers(HttpMethod.PUT, "/curso").hasAnyRole("ADMIN","VITIMA")
                        .requestMatchers(HttpMethod.DELETE, "/curso").hasAnyRole("ADMIN","VITIMA")
                        .requestMatchers(HttpMethod.POST, "/experiencia/create").hasAnyRole("VITIMA","ADMIN")
                        .requestMatchers(HttpMethod.GET, "/experiencia").hasAnyRole("VITIMA","ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/experiencia").hasAnyRole("VITIMA","ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/experiencia").hasAnyRole("VITIMA","ADMIN")
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
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Permitir o front-end em localhost:3000
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos permitidos
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Cabeçalhos permitidos
        configuration.setAllowCredentials(true); // Permitir credenciais (cookies, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplicar para todos os endpoints

        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
