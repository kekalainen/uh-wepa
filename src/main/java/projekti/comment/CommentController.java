package projekti.comment;

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

import projekti.photo.Photo;
import projekti.photo.PhotoRepository;
import projekti.post.Post;
import projekti.post.PostRepository;
import projekti.user.User;
import projekti.user.UserService;

@RestController
@RequestMapping("/api/users/{slug}")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PhotoRepository photoRepository;

    @GetMapping("/posts/{id}/comments")
    public Page<Comment> listByPost(@PathVariable("id") Long id, Pageable pageable) {
        return commentService.listByPost(postRepository.getOne(id), pageable);
    }

    @PostMapping("/posts/{id}/comments")
    public Comment storeForPost(@PathVariable("id") Long id, @Valid @RequestBody Comment comment, Principal principal) {
        User user = userService.findByHandle(principal.getName());
        Post post = postRepository.getOne(id);
        return commentService.store(comment, user, post);
    }

    @GetMapping("/photos/{id}/comments")
    public Page<Comment> listByPhoto(@PathVariable("id") Long id, Pageable pageable) {
        return commentService.listByPhoto(photoRepository.getOne(id), pageable);
    }

    @PostMapping("/photos/{id}/comments")
    public Comment storeForPhoto(@PathVariable("id") Long id, @Valid @RequestBody Comment comment, Principal principal) {
        User user = userService.findByHandle(principal.getName());
        Photo photo = photoRepository.getOne(id);
        return commentService.store(comment, user, photo);
    }
}
