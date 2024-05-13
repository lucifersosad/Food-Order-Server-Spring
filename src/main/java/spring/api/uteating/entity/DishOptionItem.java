package spring.api.uteating.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dish_option_item")
public class DishOptionItem {
    @Id
    private Long id;

    private String name;

    private int weight;

    private int max_quantity;

    private Float price;

    @Nullable
    @Column(columnDefinition = "BOOLEAN")
    private boolean is_default;

    @ManyToOne
    @JoinColumn(name="option_dish_id")
    private OptionDish optionDish;
}
