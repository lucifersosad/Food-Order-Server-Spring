package spring.api.uteating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    private String productId;
    private String productName;
    private String productImage1;
    private String productImage2;
    private String productImage3;
    private String productImage4;
    private int productPrice;
    private String productType;
    private int remainAmount;
    private int sold;
    private String description;
    private Double ratingStar;
    private int ratingAmount;
    private String publisherId;
    private String state;
    private boolean isChecked;
}
