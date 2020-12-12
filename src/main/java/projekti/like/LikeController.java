package projekti.like;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class LikeController {

    @Autowired
    LikeService likeService;

    @Autowired
    UserService userService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PhotoRepository photoRepository;

    @PostMapping("/posts/{id}/likes")
    public List<Like> likePost(@PathVariable("id") Long id, Principal principal) {
        User user = userService.findByHandle(principal.getName());
        Post post = postRepository.getOne(id);
        likeService.store(user, post);
        return post.getLikes();
    }

    @PostMapping("/photos/{id}/likes")
    public List<Like> likePhoto(@PathVariable("id") Long id, Principal principal) {
        User user = userService.findByHandle(principal.getName());
        Photo photo = photoRepository.getOne(id);
        likeService.store(user, photo);
        return photo.getLikes();
    }

    @DeleteMapping("/posts/{id}/likes")
    public List<Like> unlikePost(@PathVariable("id") Long id, Principal principal) {
        User user = userService.findByHandle(principal.getName());
        Post post = postRepository.getOne(id);
        likeService.destroy(user, post);
        return post.getLikes();
    }

    @DeleteMapping("/photos/{id}/likes")
    public List<Like> unlikePhoto(@PathVariable("id") Long id, Principal principal) {
        User user = userService.findByHandle(principal.getName());
        Photo photo = photoRepository.getOne(id);
        likeService.destroy(user, photo);
        return photo.getLikes();
    }
}
