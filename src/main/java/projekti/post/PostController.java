package projekti.post;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekti.user.UserService;

@RestController
@RequestMapping("/api/users/{slug}/posts")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @GetMapping
    public Page<Post> list(@PathVariable("slug") String slug, Pageable pageable) {
        return postService.listByRecipient(slug, pageable);
    }

    @PostMapping
    public Post store(@PathVariable("slug") String slug, @Valid @RequestBody Post post, Principal principal) {
        post.setRecipient(userService.findBySlug(slug));
        post.setAuthor(userService.findByHandle(principal.getName()));
        return postService.store(post);
    }
}
