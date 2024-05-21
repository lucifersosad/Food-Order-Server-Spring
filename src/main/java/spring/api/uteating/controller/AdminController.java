package spring.api.uteating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.api.uteating.entity.Product;
import spring.api.uteating.model.ErrorReponse;
import spring.api.uteating.model.ProductModel;
import spring.api.uteating.model.UserModel;
import spring.api.uteating.service.IProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    IProductService productService;
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome admin";
    }

    @PutMapping("/product/check")
    public ResponseEntity<?> checkProduct(@RequestBody UserModel usermodel, @RequestParam String productId) {
        if (usermodel.isAdmin()) {
            Optional<Product> optional = productService.findById(Long.parseLong(productId));
            if (optional.isPresent()) {
                Product product = optional.get();
                if (product.isChecked()) {
                    product.setChecked(false);
                } else {
                    product.setChecked(true);
                }
                productService.save(product);
                return ResponseEntity.status(HttpStatus.OK).body(productService.convertToProductModel(product));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorReponse("Ban khong phai admin"));
        }
    }
}
