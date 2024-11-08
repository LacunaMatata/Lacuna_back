package LacunaMatata.Lacuna.dto.response.admin.statistic;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespUserProblemStatisticCount {
    private Integer userProblemOne;
    private Integer userProblemTwo;
}
