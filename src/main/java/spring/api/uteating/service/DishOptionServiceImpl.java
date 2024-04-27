package spring.api.uteating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.api.uteating.entity.DishOption;
import spring.api.uteating.model.DishOptionItemModel;
import spring.api.uteating.model.DishOptionModel;
import spring.api.uteating.model.OptionItemModel;
import spring.api.uteating.repository.DishOptionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishOptionServiceImpl implements IDishOptionService{
    @Autowired
    DishOptionRepository dishOptionRepository;

    @Autowired
    DishOptionItemServiceImpl dishOptionItemService;

    public List<DishOption> findDishOptionByDishId(Long dish_id) {
        return dishOptionRepository.findDishOptionByDishId(dish_id);
    }

    public List<DishOptionModel> findDishOptionModelByDishId(Long dish_id)  {
        List<DishOption> dishOptions = findDishOptionByDishId(dish_id);
        List<DishOptionModel> dishOptionModels = new ArrayList<>();
        for (DishOption dishOption : dishOptions) {
            DishOptionModel dishOptionModel = new DishOptionModel();
            dishOptionModel.setId(dishOption.getOptionDish().getId());
            dishOptionModel.setName(dishOption.getOptionDish().getName());
            dishOptionModel.setMandatory(dishOption.isMandatory());
            List<DishOptionItemModel> dishOptionItemModels = dishOptionItemService.getDishOptionItemModels(dishOption.getOptionDish());
            dishOptionModel.setOption_items(new OptionItemModel(dishOption.getMin_select(), dishOption.getMax_select(),dishOptionItemModels));
            dishOptionModels.add(dishOptionModel);
        }
        return dishOptionModels;
    }



}
