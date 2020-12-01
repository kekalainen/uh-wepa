package projekti.friendship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekti.exception.NotFoundException;
import projekti.user.User;
import projekti.user.UserService;

@Service
public class FriendshipService {

    @Autowired
    FriendshipRepository friendshipRepository;

    @Autowired
    UserService userService;

    public Friendship show(User one, User two) {
        Friendship friendship = friendshipRepository.findByRequesterAndRequesteeOrRequesterAndRequestee(one, two, two, one);
        if (friendship == null)
            throw new NotFoundException();
        return friendship;
    }

    public Friendship store(User requester, User requestee) {
        Friendship friendship = new Friendship();
        friendship.setRequester(requester);
        friendship.setRequestee(requestee);
        friendship.setAccepted(false);
        return friendshipRepository.save(friendship);
    }

    public Friendship accept(User requester, User requestee) {
        Friendship friendship = friendshipRepository.findByRequesterAndRequestee(requester, requestee);
        friendship.setAccepted(true);
        return friendshipRepository.save(friendship);
    }

    public void destroy(User one, User two) {
        Friendship friendship = friendshipRepository.findByRequesterAndRequesteeOrRequesterAndRequestee(one, two, two, one);
        if (friendship == null)
            throw new NotFoundException();
        friendshipRepository.delete(friendship);
    }

    public boolean areFriends(User one, User two) {
        if (one.equals(two))
            return true;
        Friendship friendship = friendshipRepository.findByRequesterAndRequesteeOrRequesterAndRequestee(one, two, two, one);
        return friendship != null && friendship.isAccepted();
    }
}
