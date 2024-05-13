package spring.api.uteating.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantModel {
    private Long restaurant_id;
    private String name;
    private String phone;
    private RatingModel rating;
    private String address;
    private List<DateTime> available_times;
    private Position position;
    @JsonProperty("isOpen")
    private boolean isOpen;
}
