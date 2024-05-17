package spring.api.uteating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.api.uteating.model.MenuResponse;
import spring.api.uteating.model.ProductModel;
import spring.api.uteating.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    IProductService productService;
    @GetMapping("")
    public ResponseEntity<List<ProductModel>> getListProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/filter")
    public String filterProduct(@RequestParam String type) {
        return "Có cc";
    }
}
