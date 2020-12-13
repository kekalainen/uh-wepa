package projekti.post;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projekti.like.Like;
import projekti.user.User;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
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
    @Column(length = 5000)
    private String content;
    @OneToMany(mappedBy = "post")
    private List<Like> likes;
    @CreationTimestamp
    private Date createdAt;
}
