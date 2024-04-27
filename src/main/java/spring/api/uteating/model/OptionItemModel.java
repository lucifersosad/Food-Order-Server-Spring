package spring.api.uteating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionItemModel {
    private int min_select;

    private int max_select;

    private List<DishOptionItemModel> items;
}
