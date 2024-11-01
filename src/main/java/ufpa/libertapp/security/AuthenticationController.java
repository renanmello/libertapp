package ufpa.libertapp.security;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ufpa.libertapp.user.User;
import ufpa.libertapp.user.UserRepository;

/**
 * Controlador REST para autenticação e registro de usuários.
 * <p>
 * Este controlador fornece endpoints para login e registro de novos usuários,
 * incluindo a geração de tokens JWT para autenticação.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    /**
     * Realiza a autenticação de um usuário e retorna um token JWT se as credenciais forem válidas.
     *
     * @param data objeto {@link AuthenticationDTO} contendo as credenciais do usuário
     * @return uma resposta HTTP com o token JWT, as autoridades do usuário e seu ID se a autenticação for bem-sucedida
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        User user = (User) userRepository.findByLogin(data.login());
        System.out.println(auth.getPrincipal());
        System.out.println(auth.getDetails());
        System.out.println(auth.isAuthenticated());

        // Gera um token JWT para o usuário autenticado
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token, auth.getAuthorities(), user.getId()));
    }

    /**
     * Registra um novo usuário no sistema com as informações fornecidas.
     *
     * @param data objeto {@link RegisterDTO} contendo as informações de login, senha e papel do usuário
     * @return uma resposta HTTP com o ID e o login do novo usuário, ou erro 400 se o login já existir
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String crippass = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), crippass, data.role());// Login já existe

        this.userRepository.save(newUser);
        return ResponseEntity.ok(new RegisterResponseDTO(newUser.getId(), newUser.getLogin()));
    }
}
