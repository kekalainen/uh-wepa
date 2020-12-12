package projekti.photo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import projekti.user.User;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findAllByAuthor(User author);
    @EntityGraph(attributePaths = {"likes"})
    Page<Photo> findAllByAuthor(User author, Pageable pageable);
}
