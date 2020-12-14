package projekti;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;

import projekti.friendship.FriendshipService;
import projekti.user.User;

public class FriendshipTest extends BaseTest {

    @Autowired
    private FriendshipService friendshipService;

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

    @Test
    public void testUi() {
        User test = authenticate();
        User other = new User("other", "other", "other", "password", null);
        userService.store(other);

        goTo(baseUrl + "/profiles/other");
        driverWait().until(ExpectedConditions.presenceOfElementLocated(By.tagName("button")));
        assertTrue(pageSource().contains("Add friend"));
        friendshipService.store(other, test);
        goTo(baseUrl + "/profiles/other");
        driverWait().until(ExpectedConditions.presenceOfElementLocated(By.tagName("button")));
        assertFalse(pageSource().contains("Add friend"));
        assertTrue(pageSource().contains("Accept"));
        friendshipService.accept(other, test);
        goTo(baseUrl + "/profiles/other");
        driverWait().until(ExpectedConditions.presenceOfElementLocated(By.tagName("button")));
        assertTrue(pageSource().contains("Unfriend"));
    }
}
