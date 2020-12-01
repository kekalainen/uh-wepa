package projekti.post;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projekti.user.User;

@Entity
@AllArgsConstructor @Data @NoArgsConstructor
public class Post extends AbstractPersistable<Long> {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User recipient;
    @ManyToOne
    private User author;
    @ManyToOne
    private Post parent;
    @NotBlank
    private String content;
    @CreationTimestamp
    private Date createdAt;
}
