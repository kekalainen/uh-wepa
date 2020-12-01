package projekti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnsupportedMediaTypeException extends ResponseStatusException {
    public UnsupportedMediaTypeException() {
        super(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
