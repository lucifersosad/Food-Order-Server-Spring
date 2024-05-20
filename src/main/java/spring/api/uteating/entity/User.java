package spring.api.uteating.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    private String userId;

    private String username;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    private String gender;

    private String phone;

    @Column(name = "avatar_url")
    private String avatarURL;

    private String password;

    @Column(columnDefinition = "BOOLEAN")
    private Boolean enabled;

    @OneToMany(mappedBy = "user")
    private List<Product> products;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
