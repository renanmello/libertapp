package ufpa.libertapp.user;

public interface UserService {
    User create(User user);
    User View(String username, String password);
}
