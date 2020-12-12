package projekti.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekti.user.User;
import projekti.exception.UnauthorizedException;
import projekti.friendship.FriendshipService;
import projekti.photo.Photo;
import projekti.post.Post;

@Service
public class LikeService {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    FriendshipService friendshipService;

    public Like store(User user, Post post) {
        if (!friendshipService.areFriends(user, post.getAuthor()))
            throw new UnauthorizedException();
        Like like = new Like();
        like.setAuthor(user);
        like.setPost(post);
        return likeRepository.save(like);
    }

    public Like store(User user, Photo photo) {
        if (!friendshipService.areFriends(user, photo.getAuthor()))
            throw new UnauthorizedException();
        Like like = new Like();
        like.setAuthor(user);
        like.setPhoto(photo);
        return likeRepository.save(like);
    }

    public void destroy(User user, Post post) {
        likeRepository.delete(likeRepository.findByAuthorAndPost(user, post));
    }

    public void destroy(User user, Photo photo) {
        likeRepository.delete(likeRepository.findByAuthorAndPhoto(user, photo));
    }
}
