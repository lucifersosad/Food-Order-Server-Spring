package spring.api.uteating.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DishOptionKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long option_dish_id;
    private Long dish_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishOptionKey ratingKey = (DishOptionKey) o;
        return Objects.equals(option_dish_id, ratingKey.option_dish_id) &&
                Objects.equals(dish_id, ratingKey.dish_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(option_dish_id, dish_id);
    }
}
