package projekti;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/api/users/{slug}")
    public User show(@PathVariable("slug") String slug) {
        return userService.findBySlug(slug);
    }

    @PostMapping("/api/users/login")
    public User login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/api/users/signup")
    public User signup(@Valid @RequestBody User user) {
        return userService.signup(user);
    }
}
