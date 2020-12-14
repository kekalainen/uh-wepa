package projekti;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import projekti.friendship.FriendshipService;
import projekti.user.User;
import projekti.user.UserService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendshipTest {

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private UserService userService;

    @Test
    public void testAreFriends() {
        User one = new User("one", "one", "one", "password", null);
        userService.store(one);
        User two = new User("two", "two", "two", "password", null);
        userService.store(two);
        assertFalse(friendshipService.areFriends(one, two));
        friendshipService.store(one, two);
        assertFalse(friendshipService.areFriends(one, two));
        friendshipService.accept(one, two);
        assertTrue(friendshipService.areFriends(one, two));
    }
}
