package projekti.friendship;

import org.springframework.data.jpa.repository.JpaRepository;

import projekti.user.User;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    Friendship findByRequesterAndRequestee(User requester, User requestee);
    Friendship findByRequesterAndRequesteeOrRequesterAndRequestee(User one, User two, User two_two, User one_two);
}
