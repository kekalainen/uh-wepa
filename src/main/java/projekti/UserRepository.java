package projekti;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByHandle(String slug);
    User findBySlug(String slug);
}
