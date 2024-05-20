package spring.api.uteating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.api.uteating.entity.Product;
import spring.api.uteating.model.BillInfo;
import spring.api.uteating.model.MenuResponse;
import spring.api.uteating.model.ProductModel;
import spring.api.uteating.service.IProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<ProductModel>> filterProduct(@RequestParam String type) {
        return ResponseEntity.ok(productService.getProductsByType(type));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductModel>> searchProduct(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.getProductsByKeyword(keyword));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductModel> productDetail(@PathVariable("productId") String productId) {
        return ResponseEntity.ok(productService.getProductById(Long.parseLong(productId)));
    }
    @GetMapping("/getProductsByBillInfos")
    public ResponseEntity<List<ProductModel>> getProductsByBillInfos(@RequestBody List<BillInfo> billInfos) {
        List<ProductModel> productModels = new ArrayList<>();
        for (BillInfo billInfo : billInfos) {
            productModels.add(productService.getProductById(Long.parseLong(billInfo.getProductId())));
        }
        return ResponseEntity.ok(productModels);
    }
}
