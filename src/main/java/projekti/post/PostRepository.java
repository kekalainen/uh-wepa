package projekti.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import projekti.user.User;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByRecipientAndId(User recipient, Long id);
    @EntityGraph(attributePaths = {"author", "likes"})
    Page<Post> findAllByRecipient(User recipient, Pageable pageable);
}
