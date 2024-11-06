package LacunaMatata.Lacuna.dto.response.admin.product;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespLowerProductCategoryDto {
    private Integer productLowerCategoryId;
    private Integer productUpperCategoryId;
    private String productLowerCategoryName;
}
