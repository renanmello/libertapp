package ufpa.libertapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) {
        User existUser = userRepository.findByUsername(user.getUsername());
        if (existUser != null) {
            throw new Error("User already Exist");
        }
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    @Override
    public User View(String username, String password) {
        User user = userRepository.findByUsername(username);

        if(user.getUsername().equals(username)  && passwordEncoder().matches(password,user.getPassword())){
            return userRepository.findByUsername(username);
        }
        throw new Error("User not found");
    }
}
