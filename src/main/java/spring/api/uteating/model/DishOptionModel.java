package spring.api.uteating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishOptionModel {
    private boolean mandatory;

    private Long id;

    private String name;

    private OptionItemModel option_items;
}
