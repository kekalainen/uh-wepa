package projekti.photo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import projekti.exception.UnauthorizedException;
import projekti.exception.UnsupportedMediaTypeException;
import projekti.friendship.FriendshipService;
import projekti.user.User;
import projekti.user.UserService;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    private UserService userService;

    @Autowired FriendshipService friendshipService;

    public Page<Photo> listByAuthor(String slug, Pageable pageable) {
        return photoRepository.findAllByAuthor(userService.findBySlug(slug), pageable);
    }

    public Photo getOne(Long id) {
        return photoRepository.getOne(id);
    }

    public ResponseEntity<byte[]> show(Long id) {
        Photo photo = photoRepository.getOne(id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(photo.getContentType()));
        headers.setContentLength(photo.getSize());
        return new ResponseEntity<>(photo.getContent(), headers, HttpStatus.OK);
    }

    public Photo store(MultipartFile content, String description, User author) throws IOException {
        if (photoRepository.findAllByAuthor(author).size() >= 10)
            throw new UnauthorizedException();
        if (!content.getContentType().startsWith("image/"))
            throw new UnsupportedMediaTypeException();
        Photo photo = new Photo();
        photo.setContent(content.getBytes());
        photo.setContentType(content.getContentType());
        photo.setSize(content.getSize());
        photo.setDescription(description);
        photo.setAuthor(author);
        return photoRepository.save(photo);
    }

    public void destroy(Photo photo) {
        photoRepository.delete(photo);
    }
}
