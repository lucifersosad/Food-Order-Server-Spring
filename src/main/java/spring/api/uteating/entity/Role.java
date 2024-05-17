package spring.api.uteating.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name", length = 50, columnDefinition = "nvarchar(50) not null")
    private String name;
    @Override
    public String toString() {
        return name.toString();
    }

}
