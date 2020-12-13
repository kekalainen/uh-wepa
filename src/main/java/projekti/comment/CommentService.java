package projekti.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import projekti.user.User;
import projekti.exception.UnauthorizedException;
import projekti.friendship.FriendshipService;
import projekti.photo.Photo;
import projekti.post.Post;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    FriendshipService friendshipService;

    public Page<Comment> listByPost(Post post, Pageable pageable) {
        return commentRepository.findAllByPost(post, pageable);
    }

    public Comment store(Comment comment, User author, Post post) {
        if (!friendshipService.areFriends(author, post.getRecipient()))
            throw new UnauthorizedException();
        comment.setAuthor(author);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public Page<Comment> listByPhoto(Photo photo, Pageable pageable) {
        return commentRepository.findAllByPhoto(photo, pageable);
    }

    public Comment store(Comment comment, User author, Photo photo) {
        if (!friendshipService.areFriends(author, photo.getAuthor()))
            throw new UnauthorizedException();
        comment.setAuthor(author);
        comment.setPhoto(photo);
        return commentRepository.save(comment);
    }
}
