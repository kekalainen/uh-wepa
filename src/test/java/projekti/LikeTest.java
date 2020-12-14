package projekti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import projekti.exception.UnauthorizedException;
import projekti.friendship.FriendshipService;
import projekti.like.LikeRepository;
import projekti.like.LikeService;
import projekti.post.Post;
import projekti.post.PostService;
import projekti.user.User;

public class LikeTest extends BaseTest {

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private PostService postService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private LikeRepository likeRepository;

    @Test
    public void testLikeForPost() {
        User one = new User("one", "one", "one", "password", null);
        userService.store(one);
        User two = new User("two", "two", "two", "password", null);
        userService.store(two);
        friendshipService.store(one, two);

        Post post = new Post();
        post.setAuthor(one);
        post.setRecipient(two);
        post.setContent("Test");

        assertThrows(UnauthorizedException.class, () -> {
            postService.store(post);
        });

        friendshipService.accept(one, two);
        postService.store(post);

        likeService.store(one, post);
        assertEquals(likeRepository.findByAuthorAndPost(one, post).getAuthor(), one);

        assertThrows(DataIntegrityViolationException.class, () -> {
            likeService.store(one, post);
        });
    }
}
