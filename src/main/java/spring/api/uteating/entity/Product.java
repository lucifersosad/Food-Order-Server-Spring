package spring.api.uteating.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private int productPrice;

    private String productType;

    private int remainAmount;

    private int sold;

    private String description;

    private String state;

    private String productImage1;

    private String productImage2;

    private String productImage3;

    private String productImage4;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "rating_star")
    private Double ratingStar;

    @Column(name = "rating_amount")
    private int ratingAmount;

    @Column(name = "is_checked", columnDefinition = "BOOLEAN")
    private boolean isChecked;
}
