package ufpa.libertapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ufpa.libertapp.user.UserRepository;

/**
 * Serviço de autorização que implementa o {@link UserDetailsService} para autenticação de usuários.
 * <p>
 * Esta classe é responsável por carregar os detalhes de um usuário com base no nome de usuário,
 * utilizando o repositório de usuários para buscar o usuário pelo login.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * Carrega os detalhes do usuário com base no nome de usuário fornecido.
     *
     * @param username o nome de usuário do usuário a ser autenticado
     * @return um objeto {@link UserDetails} contendo as informações do usuário
     * @throws UsernameNotFoundException se o usuário com o nome fornecido não for encontrado
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }
}
