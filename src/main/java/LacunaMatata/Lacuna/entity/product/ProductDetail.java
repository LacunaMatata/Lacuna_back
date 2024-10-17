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
public class ProductDetail {
    private int productDetailId;
    private int productId;
    private String detailName;
    private String detailDescription;
    private BigDecimal detailOptionPrice;
    private int detailRegisterId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
