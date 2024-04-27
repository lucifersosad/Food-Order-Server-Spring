package spring.api.uteating.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishOptionItemModel {
    private String name;

    private int weight;

    private int max_quantity;

    private Long id;

    @JsonProperty("is_default")
    private boolean is_default;

    @JsonProperty("price")
    private Price priceModel;
}
