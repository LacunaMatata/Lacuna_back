package LacunaMatata.Lacuna.entity.product;

import LacunaMatata.Lacuna.entity.user.User;
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
    private int productProductUpperCategoryId;
    private int productLowerCategoryId;
    private String productCode;
    private String productName;
    private BigDecimal price;
    private BigDecimal promotionPrice;
    private String productImg;
    private int productRegisterId;
    private String description;
    private String etc;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // 서브쿼리 계산용
    private int totalCount;

    // join할 테이블
    private User user;
    private ProductUpperCategory productUpperCategory;
    private ProductLowerCategory productLowerCategory;
}
