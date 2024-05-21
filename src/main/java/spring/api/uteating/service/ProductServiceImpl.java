package spring.api.uteating.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.api.uteating.entity.Product;
import spring.api.uteating.entity.User;
import spring.api.uteating.model.ProductCartModel;
import spring.api.uteating.model.ProductDTO;
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
        List<ProductModel> productModels = productRepository.findByType(type)
                .stream()
                .map(this::convertToProductModel)
                .collect(Collectors.toList());
        return productModels;
    }

    @Override
    public List<ProductModel> getAllProduct() {
        List<ProductModel> productModels = new ArrayList<>();
        List<Product> products = productRepository.findByAllDesc();
        for (Product product : products) {
            ProductModel productModel = getProductById(product.getId());
            productModels.add(productModel);
        }
        return productModels;
    }



    @Override
    public List<ProductModel> getProductByUserId(String publisherId) {
        List<ProductModel> productModels = productRepository.findProductsByPublisherId(publisherId)
                .stream()
                .map(this::convertToProductModel)
                .collect(Collectors.toList());
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
    public ProductCartModel getProductCartById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return productRepository.findProductCartByProductId(productId);
        }
        return null;
    }

//    public List<ProductModel> confirmProducts(List<ProductModel> productModels) {
//        for (ProductModel productModel : productModels) {
//            Optional<Product> productOptional = productRepository.findById(Long.parseLong(productModel.getProductId()));
//            if (productOptional.isPresent()) {
//                Product product = productOptional.get();
//                if (productModel.getRemainAmount())
//                save(product);
//            }
//        }
//    }

    @Override
    public Product addProduct(Product product, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        product.setUser(user);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductDTO productDTO) {
        Optional<Product> optProduct = findById(Long.parseLong(productDTO.getProductId()));
        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            BeanUtils.copyProperties(productDTO, product);
            save(product);
            return product;
        }
        return null;
    }

    @Override
    public ProductModel convertToProductModel(Product product) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(product, productModel);
        productModel.setProductId(String.valueOf(product.getId()));
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

    @Override
    public Optional<Product> findById(Long aLong) {
        return productRepository.findById(aLong);
    }
}
