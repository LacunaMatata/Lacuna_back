package LacunaMatata.Lacuna.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private int productId;
    private int productLowerCategoryId;
    private String productCode;
    private String productName;
    private String productDescription;
    private BigDecimal price;
    private BigDecimal promotionPrice;
    private String productImg;
    private int productRegisterId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
