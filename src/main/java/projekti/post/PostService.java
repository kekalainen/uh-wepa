package projekti.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import projekti.exception.UnauthorizedException;
import projekti.friendship.FriendshipService;
import projekti.user.User;
import projekti.user.UserService;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired FriendshipService friendshipService;

    public Post show(User recipient, Long id) {
        return postRepository.findByRecipientAndId(recipient, id);
    }

    public Page<Post> listByRecipient(String slug, Pageable pageable) {
        return postRepository.findAllByRecipient(userService.findBySlug(slug), pageable);
    }

    public Post store(Post post) {
        if (!friendshipService.areFriends(post.getAuthor(), post.getRecipient()))
            throw new UnauthorizedException();
        return postRepository.save(post);
    }
}
