package spring.api.uteating.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ProductDTO {
    private String productId;
    @NotNull(message="Phải đủ số lượng ảnh")
    private String productImage1;
    @NotNull(message="Phải đủ số lượng ảnh")
    private String productImage2;
    @NotNull(message="Phải đủ số lượng ảnh")
    private String productImage3;
    @NotNull(message="Phải đủ số lượng ảnh")
    private String productImage4;
    @NotNull(message = "Ten không được để trống")
    private String productName;
    @NotNull(message = "Kieu không được để trống")
    private String productType;
    @NotNull(message="Giá không được để trống")
    private Integer productPrice;
    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 100, message = "It nhat 100 san pham")
    private Integer remainAmount;
    @NotNull(message = "Mô tả không được để trống")
    private String description;
    @NotNull(message = "Id người bán không được để trống")
    private String publisherId;
}
