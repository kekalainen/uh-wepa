package projekti.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public Page<User> list(@RequestParam(defaultValue = "") String name, Pageable pageable) {
        return userService.listByName(name, pageable);
    }

    @GetMapping("/{slug}")
    public User show(@PathVariable("slug") String slug) {
        return userService.findBySlug(slug);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/signup")
    public User signup(@Valid @RequestBody User user) {
        return userService.signup(user);
    }
}
