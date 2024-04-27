package spring.api.uteating.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.api.uteating.entity.DishOptionItem;
import spring.api.uteating.entity.OptionDish;
import spring.api.uteating.model.DishOptionItemModel;
import spring.api.uteating.model.Price;
import spring.api.uteating.repository.DishOptionItemRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class DishOptionItemServiceImpl implements IDishOptionItemService{
    @Autowired
    DishOptionItemRepository dishOptionItemRepository;

    List<DishOptionItemModel> getDishOptionItemModels(OptionDish optionDish) {
        List<DishOptionItem> dishOptionItems = optionDish.getDishOptionItems();
        List<DishOptionItemModel> dishOptionItemModels = new ArrayList<>();
        for (DishOptionItem dishOptionItem : dishOptionItems) {
            DishOptionItemModel dishOptionItemModel = new DishOptionItemModel();
            BeanUtils.copyProperties(dishOptionItem, dishOptionItemModel);
            dishOptionItemModel.setPriceModel(new Price(dishOptionItem.getPrice()));
            dishOptionItemModels.add(dishOptionItemModel);
        }
        return dishOptionItemModels;
    }
}
