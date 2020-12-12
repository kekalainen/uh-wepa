package projekti.like;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(
    name = "\"Like\"",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"author_id", "post_id"}),
        @UniqueConstraint(columnNames = {"author_id", "photo_id"})
    }
)
@AllArgsConstructor @Data @NoArgsConstructor
public class Like extends AbstractPersistable<Long> {

    @ManyToOne
    private User author;
    @JsonIgnore
    @ManyToOne
    private Post post;
    @JsonIgnore
    @ManyToOne
    private Photo photo;
    @CreationTimestamp
    private Date createdAt;
}
