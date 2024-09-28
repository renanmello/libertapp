package ufpa.libertapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/view")
    public User view(@RequestBody User user) {
        return userService.View(user.getUsername(), user.getPassword());
    }
}
