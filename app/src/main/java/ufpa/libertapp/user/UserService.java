package ufpa.libertapp.user;

import java.util.Optional;

public interface UserService {
    User create(User user);
    User view(Long id);
    User update(User user,Long id);

}
