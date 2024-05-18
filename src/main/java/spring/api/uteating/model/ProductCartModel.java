package spring.api.uteating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProductCartModel {
    private String productId;
    private String productName;
    private String productImage1;
    private int productPrice;
    private int remainAmount;

    public ProductCartModel(Long id, String productName, String productImage1, int productPrice, int remainAmount) {
        this.productId = String.valueOf(id);
        this.productName = productName;
        this.productImage1 = productImage1;
        this.productPrice = productPrice;
        this.remainAmount = remainAmount;
    }
}


