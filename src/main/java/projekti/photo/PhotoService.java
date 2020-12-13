package projekti.photo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

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

    public Photo findByAuthorAndId(User author, Long id) {
        return photoRepository.findByAuthorAndId(author, id);
    }

    public ResponseEntity<byte[]> show(Long id, boolean square) throws IOException {
        Photo photo = photoRepository.getOne(id);
        if (square)
            photo = this.square(photo);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(photo.getContentType()));
        headers.setContentLength(photo.getSize());
        headers.setCacheControl("max-age=3600;");
        return new ResponseEntity<>(photo.getContent(), headers, HttpStatus.OK);
    }

    /**
     * Returns a squared version of a given photo.
     * 
     * Used for user avatars
     */
    public Photo square(Photo photo) throws IOException {
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(photo.getContent()));

        int height = img.getHeight();
        int width = img.getWidth();

        if (height == width)
            return photo;

        int wh = (width > height ? height : width);

        BufferedImage cropped = img.getSubimage((width / 2) - (wh / 2), (height / 2) - (wh / 2), wh, wh);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(cropped, photo.getContentType().replace("image/", ""), out);

        photo.setContent(out.toByteArray());
        photo.setSize(Long.valueOf(out.size()));

        return photo;
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
