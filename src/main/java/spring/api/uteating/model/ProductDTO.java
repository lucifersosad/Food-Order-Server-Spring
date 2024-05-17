package spring.api.uteating.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ProductDTO {
    @NotNull(message="Phải đủ số lượng ảnh")
    private String productImage1;
    @NotNull(message="Phải đủ số lượng ảnh")
    private String productImage2;
    @NotNull(message="Phải đủ số lượng ảnh")
    private String productImage3;
    @NotNull(message="Phải đủ số lượng ảnh")
    private String productImage4;
    @NotNull(message = "Ten không được để trống")
    private String productType;
    @NotNull(message="Giá không được để trống")
    private Integer productPrice;
    @NotNull(message = "Te không được để trống")
    private String productName;
    @NotNull(message = "Số lượng không được để trống")
    private Integer remainAmount;
    @NotNull(message = "Mô tả không được để trống")
    private String description;
}
