package ufpa.libertapp.user;

public interface UserService {
    User create(User user);
    User view(String username, String password);
    User findByLogin(String username);
}
