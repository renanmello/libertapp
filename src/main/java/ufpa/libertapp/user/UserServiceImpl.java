package ufpa.libertapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User create(User user) {
        // Encrypt the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public User view(String login, String password) {
         UserDetails user = userRepository.findByLogin(login);
        // Check if the user exists and if the password is correct
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }
        return null;
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }
}
