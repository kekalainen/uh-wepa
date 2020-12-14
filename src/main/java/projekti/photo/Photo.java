package projekti.photo;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projekti.comment.Comment;
import projekti.like.Like;
import projekti.user.User;

@Entity
@AllArgsConstructor @Data @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Photo extends AbstractPersistable<Long> {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    @NotBlank
    private String contentType;
    @NotNull
    private Long size;
    @NotNull
    @JsonIgnore
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] content;
    @NotBlank
    private String description;
    @OneToMany(mappedBy = "photo", cascade = CascadeType.REMOVE)
    private List<Like> likes;
    @JsonIgnore
    @OneToMany(mappedBy = "photo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> comments;
    @CreationTimestamp
    private Date createdAt;
}
