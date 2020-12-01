package projekti.photo;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projekti.user.User;

@Entity
@AllArgsConstructor @Data @NoArgsConstructor
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
    @CreationTimestamp
    private Date createdAt;
}