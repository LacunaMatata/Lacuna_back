package LacunaMatata.Lacuna.dto.response.admin.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RespLowerCategoryAndProductDto {
    private Integer productLowerCategoryId;
    private Integer productUpperCategoryId;
    private String productLowerCategoryName;
}
