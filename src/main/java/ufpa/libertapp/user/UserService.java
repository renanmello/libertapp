package ufpa.libertapp.user;

import org.springframework.stereotype.Service;


public interface UserService {
    User create(User user);
    User View(String username, String password);
}
