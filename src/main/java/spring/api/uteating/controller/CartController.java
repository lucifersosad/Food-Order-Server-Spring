package spring.api.uteating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.api.uteating.model.MenuResponse;
import spring.api.uteating.model.ProductCartModel;
import spring.api.uteating.service.IProductService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    IProductService productService;
    @GetMapping("/productCart")
    public ProductCartModel getCartItemById(@RequestParam String idProduct) {
        return productService.getProductCartById(Long.parseLong(idProduct));
    }
}
