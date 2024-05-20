package spring.api.uteating.controller;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import spring.api.uteating.entity.Product;
import spring.api.uteating.entity.User;
import spring.api.uteating.exception.ProductException;
import spring.api.uteating.model.*;
import spring.api.uteating.service.IProductService;
import spring.api.uteating.service.UserServiceImpl;

import java.util.*;

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

    boolean isAuthorityUser(String userId, Authentication authentication) {
        String username = authentication.getName();
        User authUser = userService.getUser(username);
        return !authUser.getUserId().equals(userId);
    }

    @GetMapping("/authority/{userid}")
    public ResponseEntity<?> authority(@PathVariable ("userid") String userId, Authentication authentication) {
        try {
            if (isAuthorityUser(userId, authentication)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorReponse("Ban khong duoc cap quyen"));
            } else {
                return ResponseEntity.ok(userService.getUserModel(userId));
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorReponse(e.getMessage()));
        }
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

    @GetMapping("/products")
    public ResponseEntity<?> listProductsByPublisher(@RequestParam String publisherId) {
        try {
            List<ProductModel> productModels = productService.getProductByUserId(publisherId);
            return ResponseEntity.ok(productModels);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/product/add")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult result, Authentication authentication) {
        if (result.hasErrors()) {
            String messageError = "";
            for (FieldError error : result.getFieldErrors()) {
                messageError += error.getDefaultMessage() + " ";
            }
            return new ResponseEntity<>(messageError, HttpStatus.BAD_REQUEST);
        }

        try {
            productDTO.setSold(0);
            Product product = new Product();
            BeanUtils.copyProperties(productDTO, product);
            Product savedProduct = productService.addProduct(product, productDTO.getPublisherId());
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.convertToProductModel(savedProduct));
        } catch (Exception e) {
            throw new ProductException(e.getMessage());
        }
    }

    @PutMapping("/product/edit")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            Product savedProduct = productService.updateProduct(productDTO);
            return ResponseEntity.status(HttpStatus.OK).body(productService.convertToProductModel(savedProduct));
        } catch (Exception e) {
            throw new ProductException(e.getMessage());
        }
    }

    @PutMapping("/feedback")
    public ResponseEntity<?> feedbackUser(@Nullable @RequestParam String userId, @RequestParam int ratingAmount, @RequestParam double ratingStar, @RequestParam String productId) {
        try {
            Optional<Product> optionalProduct = productService.findById(Long.parseLong(productId));
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                product.setRatingAmount(ratingAmount);
                product.setRatingStar(ratingStar);
                productService.save(product);
                return ResponseEntity.ok(productService.getProductById(Long.parseLong(productId)));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}