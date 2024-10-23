package ufpa.libertapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);

    }

    @PutMapping("update/{userId}")
    public ResponseEntity<User> update(@RequestBody User user,@PathVariable("userId")Long userId){
        return ResponseEntity.ok(userService.update(user,userId));

    }
}
