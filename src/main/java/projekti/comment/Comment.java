package projekti.comment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projekti.photo.Photo;
import projekti.post.Post;
import projekti.user.User;

@Entity
@AllArgsConstructor @Data @NoArgsConstructor
public class Comment extends AbstractPersistable<Long> {

    @ManyToOne
    private User author;
    @NotBlank
    @Column(length = 5000)
    private String content;
    @JsonIgnore
    @ManyToOne
    private Post post;
    @JsonIgnore
    @ManyToOne
    private Photo photo;
    @CreationTimestamp
    private Date createdAt;
}
