package projekti.photo;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import projekti.exception.UnauthorizedException;
import projekti.user.UserService;

@RestController
@RequestMapping("/api/users/{slug}/photos")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @Autowired
    UserService userService;

    @GetMapping
    public Page<Photo> list(@PathVariable("slug") String slug, Pageable pageable) {
        return photoService.listByAuthor(slug, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> show(@PathVariable Long id) {
        return photoService.show(id);
    }

    @PostMapping
    public Photo store(@PathVariable("slug") String slug, @RequestParam("content") MultipartFile content, @RequestParam("description") String description, Principal principal) throws IOException{
        return photoService.store(content, description, userService.findByHandle(principal.getName()));
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id, Principal principal) {
        Photo photo = photoService.getOne(id);
        if (!photo.getAuthor().getHandle().equals(principal.getName()))
            throw new UnauthorizedException();
        photoService.destroy(photo);
    }
}
