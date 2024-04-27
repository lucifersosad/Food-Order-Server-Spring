package spring.api.uteating.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.api.uteating.entity.Restaurant;
import spring.api.uteating.model.DeliveryReponse;
import spring.api.uteating.model.Position;
import spring.api.uteating.model.RestaurantModel;
import spring.api.uteating.service.RestaurantServiceImpl;
import spring.api.uteating.utils.Converter;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
    @Autowired
    RestaurantServiceImpl restaurantService;
    @GetMapping("/getDetail")
    public ResponseEntity<DeliveryReponse> getDeliveryDetail(@RequestParam Long idRestaurant) {
        Restaurant restaurant = restaurantService.getById(idRestaurant);
        RestaurantModel restaurantModel = new RestaurantModel();
        BeanUtils.copyProperties(restaurant, restaurantModel);
        restaurantModel.setAvailable_times(Converter.convertToDateTimeList(restaurant.getAvailableDateTime()));
        restaurantModel.setRating(restaurantService.getRatingInfo(restaurant.getRatings()));
        restaurantModel.setPosition(new Position(restaurant.getLatitude(), restaurant.getLongitude()));
        restaurantModel.setOpen(restaurantService.isOpen(restaurant.getAvailableDateTime()));
        return ResponseEntity.ok(new DeliveryReponse(restaurantModel));
    }
}
