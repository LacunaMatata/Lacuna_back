package LacunaMatata.Lacuna.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductLowerCategory {
    private int productLowerCategoryId;
    private int productUpperCategoryId;
    private String productLowerCategoryName;
    private int productLowerCategoryRegisterId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
