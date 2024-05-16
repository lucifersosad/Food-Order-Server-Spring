package spring.api.uteating.service;

import spring.api.uteating.entity.Product;
import spring.api.uteating.model.ProductModel;

import java.util.List;

public interface IProductService {

    Product addProduct(Product product, String userId);

    List<ProductModel> getAllProduct();

    ProductModel getProductById(Long productId);

    List<Product> findAll();

    List<Product> findAllById(Iterable<Long> longs);

    <S extends Product> S save(S entity);

    long count();

    void deleteById(Long aLong);

    void deleteAll();
}
