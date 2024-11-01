package ufpa.libertapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação do serviço {@link UserService} para operações relacionadas à entidade {@link User}.
 * <p>
 * Esta classe fornece a lógica de negócios para criação, visualização e atualização de usuários,
 * incluindo criptografia de senha antes de salvar.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Cria um novo usuário no sistema com senha criptografada.
     *
     * @param user o objeto {@link User} contendo os dados do novo usuário
     * @return o objeto {@link User} recém-criado e salvo
     */
    @Override
    public User create(User user) {
        // Encrypt the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Recupera os dados de um usuário específico pelo ID.
     *
     * @param id o ID do usuário a ser visualizado
     * @return o objeto {@link User} correspondente ao ID fornecido, ou null se não encontrado
     */
    @Override
    public User view(Long id) {
        Optional<User> opuser = userRepository.findById(id);
        User user = opuser.get();
        // Check if the user exists and if the password is correct
        if (user.getId() != null) {
            return user;
        }
        return null;
    }

    /**
     * Atualiza a senha de um usuário existente pelo ID, criptografando-a antes de salvar.
     *
     * @param user o objeto {@link User} contendo os dados atualizados do usuário
     * @param id   o ID do usuário a ser atualizado
     * @return o objeto {@link User} atualizado
     * @throws RuntimeException se o usuário com o ID fornecido não for encontrado
     */
    @Override
    public User update(User user, Long id) {
        User edit_user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
        edit_user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(edit_user);
    }


}
