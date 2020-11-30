package projekti.friendship;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projekti.user.User;

@Entity
@AllArgsConstructor @Data @NoArgsConstructor
public class Friendship extends AbstractPersistable<Long> {

    @ManyToOne
    private User requester;
    @ManyToOne
    private User requestee;
    private boolean accepted = false;
    @CreationTimestamp
    private Date createdAt;
}
