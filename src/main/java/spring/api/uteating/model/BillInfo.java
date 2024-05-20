package spring.api.uteating.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillInfo {
    private int amount;
    private String billInfoId;
    private String productId;
    private boolean check;
}
