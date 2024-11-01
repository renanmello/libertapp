package ufpa.libertapp.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ufpa.libertapp.empresa.Empresa;
import ufpa.libertapp.orgao.Orgao;
import ufpa.libertapp.vitima.Vitima;

import java.util.Collection;
import java.util.List;

/**
 * Representa um usuário do sistema, com funcionalidades de autenticação e autorização.
 * Implementa {@link UserDetails} para integrar com o Spring Security.
 * <p>
 * Um usuário pode estar associado a uma {@link Vitima}, {@link Orgao} ou {@link Empresa}.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    /**
     * Identificador único do usuário.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome de login do usuário, pode ser tanto o cpf como email.
     */
    private String login;
    /**
     * Senha do usuário, armazenada em formato criptografado.
     */
    private String password;
    /**
     * Associação do usuário com uma vítima, caso o usuário tenha esse papel.
     */
    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private Vitima vitima;
    /**
     * Associação do usuário com um órgão, caso o usuário tenha esse papel.
     */
    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private Orgao orgao;
    /**
     * Associação do usuário com uma empresa, caso o usuário tenha esse papel.
     */
    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private Empresa empresa;
    /**
     * Papel do usuário, que define suas permissões e acessos.
     */
    private UserRole role;

    /**
     * Construtor para criar um usuário com login, senha e papel.
     *
     * @param login    nome de login do usuário
     * @param password senha do usuário
     * @param role     papel atribuído ao usuário
     */
    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /**
     * Retorna as autoridades concedidas ao usuário com base em seu papel.
     *
     * @return uma coleção de {@link GrantedAuthority} representando as permissões do usuário
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_VITIMA"), new SimpleGrantedAuthority("ROLE_EMPRESA"),
                new SimpleGrantedAuthority("ROLE_ORGAO"));
        if (this.role == UserRole.VITIMA) return List.of(new SimpleGrantedAuthority("ROLE_VITIMA"));
        if (this.role == UserRole.EMPRESA) return List.of(new SimpleGrantedAuthority("ROLE_EMPRESA"));
        if (this.role == UserRole.ORGAO) return List.of(new SimpleGrantedAuthority("ROLE_ORGAO"));

        return null;
    }

    /**
     * Retorna o nome de login do usuário.
     *
     * @return o nome de login do usuário
     */
    @Override
    public String getUsername() {
        return login;
    }

    /**
     * Indica se a conta do usuário não expirou.
     *
     * @return {@code true} se a conta não expirou; caso contrário, {@code false}
     */
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    /**
     * Indica se a conta do usuário não está bloqueada.
     *
     * @return {@code true} se a conta não está bloqueada; caso contrário, {@code false}
     */
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    /**
     * Indica se as credenciais do usuário (senha) não expiraram.
     *
     * @return {@code true} se as credenciais não expiraram; caso contrário, {@code false}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    /**
     * Indica se a conta do usuário está habilitada.
     *
     * @return {@code true} se a conta está habilitada; caso contrário, {@code false}
     */
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
