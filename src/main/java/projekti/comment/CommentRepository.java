package projekti.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import projekti.photo.Photo;
import projekti.post.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByPost(Post post, Pageable pageable);
    Page<Comment> findAllByPhoto(Photo photo, Pageable pageable);
}
