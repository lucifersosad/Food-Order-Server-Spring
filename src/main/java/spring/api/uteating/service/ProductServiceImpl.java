package spring.api.uteating.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.api.uteating.entity.Comment;
import spring.api.uteating.entity.Product;
import spring.api.uteating.entity.User;
import spring.api.uteating.model.ProductModel;
import spring.api.uteating.repository.ProductRepository;
import spring.api.uteating.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<ProductModel> getProductsByKeyword(String keyword) {
        return productRepository.findByKeyword(keyword)
                .stream()
                .map(this::convertToProductModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductModel> getProductsByType(String type) {
        return productRepository.findByType(type)
                .stream()
                .map(this::convertToProductModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductModel> getAllProduct() {
        List<ProductModel> productModels = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            ProductModel productModel = getProductById(product.getId());
            productModels.add(productModel);
        }
        Collections.reverse(productModels);
        return productModels;
    }

    @Override
    public ProductModel getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return convertToProductModel(product);
        }
        return null;
    }

    @Override
    public Product addProduct(Product product, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        product.setUser(user);
        return productRepository.save(product);
    }

    @Override
    public ProductModel convertToProductModel(Product product) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(product, productModel);
        productModel.setProductId(String.valueOf(product.getId()));

        int commentCount = 0;
        double averageRating = 0.0;
        if (product.getComments() != null) {
            double totalStars = 0.0;
            commentCount = product.getComments().size();
            for (Comment comment : product.getComments()) {
                totalStars += comment.getRating();
            }
            averageRating = (commentCount > 0) ? totalStars / commentCount : 0.0;
        }

        productModel.setRatingStar(averageRating);
        productModel.setRatingAmount(commentCount);
        productModel.setPublisherId(String.valueOf(product.getUser().getUserId()));
        return productModel;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllById(Iterable<Long> longs) {
        return productRepository.findAllById(longs);
    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        productRepository.deleteById(aLong);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
