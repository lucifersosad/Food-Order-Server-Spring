package spring.api.uteating.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
