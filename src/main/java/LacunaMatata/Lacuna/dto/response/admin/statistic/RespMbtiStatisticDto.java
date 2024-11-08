package LacunaMatata.Lacuna.dto.response.admin.statistic;

import LacunaMatata.Lacuna.dto.request.admin.statistic.ReqMbtiStatisticDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RespMbtiStatisticDto {
    private List<ReqMbtiStatisticDto> mbtiResultList;
}
