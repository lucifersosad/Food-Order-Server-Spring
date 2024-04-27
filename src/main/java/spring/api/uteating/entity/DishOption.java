package spring.api.uteating.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dish_option")
public class DishOption implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private DishOptionKey id;

    @ManyToOne
    @MapsId("dish_id")
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne
    @MapsId("option_dish_id")
    @JoinColumn(name = "option_dish_id")
    private OptionDish optionDish;

    @Nullable
    @Column(columnDefinition = "BOOLEAN")
    private boolean mandatory;

    private int min_select;

    private int max_select;
}
