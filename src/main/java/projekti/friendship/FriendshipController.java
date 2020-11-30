package projekti.friendship;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekti.user.User;
import projekti.user.UserService;

@RestController
@RequestMapping("/api/friends")
public class FriendshipController {

    @Autowired
    FriendshipService friendshipService;

    @Autowired
    UserService userService;

    @GetMapping
    public Page<User> list(Principal principal, Pageable pageable) {
        User user = userService.findByHandle(principal.getName());
        return userService.listFriends(user, pageable);
    }

    @GetMapping("/pending")
    public Page<User> listPending(Principal principal, Pageable pageable) {
        User user = userService.findByHandle(principal.getName());
        return userService.listPendingFriends(user, pageable);
    }

    /**
     * Show friendship between users.
     * 
     * Used for checking status
     */
    @GetMapping("/{slug}")
    public Friendship show(@PathVariable("slug") String slug, Principal principal) {
        User auth = userService.findByHandle(principal.getName());
        User other = userService.findBySlug(slug);
        return friendshipService.show(auth, other);
    }

    /**
     * Store a friendship request
     */
    @PostMapping("/{slug}")
    public Friendship store(@PathVariable("slug") String slug, Principal principal) {
        User requester = userService.findByHandle(principal.getName());
        User requestee = userService.findBySlug(slug);
        return friendshipService.store(requester, requestee);
    }

    /**
     * Accept an existing friendhip request
     */
    @PutMapping("/{slug}")
    public Friendship update(@PathVariable("slug") String slug, Principal principal) {
        User requestee = userService.findByHandle(principal.getName());
        User requester = userService.findBySlug(slug);
        return friendshipService.accept(requester, requestee);
    }

    /**
     * Delete a friendship (request)
     */
    @DeleteMapping("/{slug}")
    public void destroy(@PathVariable("slug") String slug, Principal principal) {
        User auth = userService.findByHandle(principal.getName());
        User other = userService.findBySlug(slug);
        friendshipService.destroy(auth, other);
    }
}
