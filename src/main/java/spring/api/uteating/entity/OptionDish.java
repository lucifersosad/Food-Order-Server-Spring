package spring.api.uteating.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "option_dish")
public class OptionDish {
    @Id
    @Column(name = "option_dish_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "optionDish")
    List<DishOptionItem> dishOptionItems;
}
