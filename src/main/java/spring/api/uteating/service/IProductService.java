package spring.api.uteating.service;

import spring.api.uteating.entity.Product;
import spring.api.uteating.model.ProductCartModel;
import spring.api.uteating.model.ProductDTO;
import spring.api.uteating.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public interface IProductService {

    ProductCartModel getProductCartById(Long productId);

    Product addProduct(Product product, String userId);

    List<ProductModel> getProductsByKeyword(String keyword);

    List<ProductModel> getProductsByType(String type);

    List<ProductModel> getAllProduct();

    List<ProductModel> getProductByUserId(String publisherId);

    ProductModel getProductById(Long productId);

    String updateProduct(ProductDTO productDTO);

    ProductModel convertToProductModel(Product product);

    List<Product> findAll();

    List<Product> findAllById(Iterable<Long> longs);

    <S extends Product> S save(S entity);

    long count();

    void deleteById(Long aLong);

    void deleteAll();
}
