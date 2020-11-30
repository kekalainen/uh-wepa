package projekti.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByHandle(String handle);
    User findBySlug(String slug);
    Page<User> findByNameContainsIgnoreCase(String name, Pageable pageable);
}
