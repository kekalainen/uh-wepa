package projekti.like;

import org.springframework.data.jpa.repository.JpaRepository;

import projekti.photo.Photo;
import projekti.post.Post;
import projekti.user.User;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Like findByAuthorAndPost(User author, Post post);
    Like findByAuthorAndPhoto(User author, Photo photo);
}
