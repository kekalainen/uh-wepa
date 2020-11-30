package projekti.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByHandle(String handle);
    User findBySlug(String slug);
    Page<User> findByNameContainsIgnoreCase(String name, Pageable pageable);

    @Query("SELECT u FROM User u JOIN Friendship f ON f.accepted = true AND ((f.requestee = ?1 AND f.requester = u) OR (f.requestee = u AND f.requester = ?1))")
    Page<User> findFriends(User user, Pageable pageable);

    @Query("SELECT u FROM User u JOIN Friendship f ON f.accepted = false AND ((f.requestee = ?1 AND f.requester = u) OR (f.requestee = u AND f.requester = ?1))")
    Page<User> findPendingFriends(User user, Pageable pageable);
}
