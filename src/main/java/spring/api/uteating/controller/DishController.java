package spring.api.uteating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.api.uteating.model.MenuResponse;
import spring.api.uteating.service.RestaurantServiceImpl;

@RestController
@RequestMapping("/api/dish")
public class DishController {
    @Autowired
    RestaurantServiceImpl restaurantService;
    @GetMapping("/getRestaurantMenu")
    public ResponseEntity<MenuResponse> getRestaurantDishes(@RequestParam Long idRestaurant) {
        return ResponseEntity.ok(new MenuResponse(restaurantService.getMenu(idRestaurant)));
    }
}
