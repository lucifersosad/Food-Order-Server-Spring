package spring.api.uteating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.api.uteating.entity.Dish;
import spring.api.uteating.entity.Product;
import spring.api.uteating.model.ProductCartModel;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p FROM Product p where p.isChecked = true AND LOWER(p.productType) = LOWER(:type) ORDER BY p.id DESC")
    List<Product> findByType(@Param("type") String type);

    @Query(value = "SELECT p FROM Product p ORDER BY p.id DESC")
    List<Product> findByAllDesc();

    @Query("SELECT p FROM Product p WHERE p.isChecked = true AND LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> findByKeyword(@Param("keyword") String keyword);

    @Query("Select new spring.api.uteating.model.ProductCartModel (p.id, p.productName, p.productImage1, p.productPrice, p.remainAmount) from Product p where p.id = :productId")
    ProductCartModel findProductCartByProductId(@Param("productId") Long productId);

    @Query(value = "SELECT p FROM Product p where p. user.userId = :publisherId ORDER BY p.id DESC")
    List<Product> findProductsByPublisherId(@Param("publisherId") String publisherId);
}
