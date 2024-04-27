package spring.api.uteating.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishTypeModel {
    private Long dish_type_id;

    private String dish_type_name;

    @JsonProperty("dishes")
    private List<DishModel> dishModels;
}
