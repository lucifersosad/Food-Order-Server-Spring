package spring.api.uteating.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    @Id
    @Column(name = "dish_id")
    private Long id;

    private String name;

    private String description;

    @Nullable
    @Column(columnDefinition = "BOOLEAN")
    private boolean is_active;

    @Nullable
    @Column(columnDefinition = "BOOLEAN")
    private boolean is_available;

    @Column(length = 1000)
    private String image;

    private Float price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="dish_type_id")
    private DishTypeEntity dishType;
}
