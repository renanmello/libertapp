package ufpa.libertapp.user;

public interface UserService {
    User create(User user);
    User view(Long id);
    User update(User user,Long id);

}
