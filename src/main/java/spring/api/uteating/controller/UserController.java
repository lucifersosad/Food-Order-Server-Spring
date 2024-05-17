package spring.api.uteating.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import spring.api.uteating.entity.Product;
import spring.api.uteating.entity.User;
import spring.api.uteating.exception.ProductException;
import spring.api.uteating.model.ProductDTO;
import spring.api.uteating.model.ProductModel;
import spring.api.uteating.model.UserModel;
import spring.api.uteating.service.IProductService;
import spring.api.uteating.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    UserServiceImpl userService;

    @Autowired
    IProductService productService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome user";
    }

    @GetMapping("/{userid}")
    public UserModel showInfornation(@PathVariable ("userid") String userId) {
        return userService.getUserModel(userId);
    }

    @PutMapping("/update")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel userModel) {
        try {
            UserModel updatedUser = userService.updateUser(userModel);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDTO productDTO, @RequestParam String userId, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            Product product = new Product();
            BeanUtils.copyProperties(productDTO, product);
            Product savedProduct = productService.addProduct(product, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.convertToProductModel(savedProduct));
        } catch (Exception e) {
            throw new ProductException(e.getMessage());
        }
    }
}