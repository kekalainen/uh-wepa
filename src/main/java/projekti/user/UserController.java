package projekti.user;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projekti.exception.UnauthorizedException;
import projekti.photo.Photo;
import projekti.photo.PhotoService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PhotoService photoService;

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

    @GetMapping("/{slug}/friends")
    public Page<User> listFriends(@PathVariable("slug") String slug, Pageable pageable) {
        return userService.listFriends(userService.findBySlug(slug), pageable);
    }

    @PutMapping("/{slug}/avatar")
    public void listFriends(@RequestParam("id") Long id, Principal principal) {
        User user = userService.findByHandle(principal.getName());
        Photo photo = photoService.getOne(id);
        if (!photo.getAuthor().equals(user))
            throw new UnauthorizedException();
        userService.setAvatar(user, photo);
    }
}
