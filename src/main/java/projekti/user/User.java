package projekti.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"User\"") // reserved keyword
@AllArgsConstructor @Data @NoArgsConstructor
public class User extends AbstractPersistable<Long> {

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String handle;
    @NotEmpty
    @Column(nullable = false)
    private String name;
    @NotEmpty
    @Column(unique = true, nullable = false)
    private String slug;
    @NotEmpty
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(final String password) {
        this.password = password;
    }
}
