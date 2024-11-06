package LacunaMatata.Lacuna.dto.response.admin.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RespUpperProductCategoryAndLowerDto {
    private Integer productUpperCategoryId;
    private String productUpperCategoryName;
    private List<RespLowerCategoryAndProductDto> productLowerCategoryAndProduct;
}
