package LacunaMatata.Lacuna.dto.response.admin.statistic;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespUserStatisticCountDto {
    private Integer userCount;
    private Integer manCount;
    private Integer womanCount;
    private Integer teenager;
    private Integer twenties;
    private Integer thirties;
    private Integer forties;
    private Integer fifties;
    private Integer andOther;
}
