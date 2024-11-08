package LacunaMatata.Lacuna.repository.admin;

import LacunaMatata.Lacuna.dto.request.admin.statistic.ReqMbtiStatisticDto;
import LacunaMatata.Lacuna.dto.response.admin.statistic.RespMbtiStatisticCountDto;
import LacunaMatata.Lacuna.dto.response.admin.statistic.RespUserProblemStatisticCount;
import LacunaMatata.Lacuna.dto.response.admin.statistic.RespUserStatisticCountDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface StatisticsMapper {
    RespUserStatisticCountDto userStatistic(ReqMbtiStatisticDto dto);
    RespUserProblemStatisticCount ProblemStatistic(ReqMbtiStatisticDto dto);
    RespMbtiStatisticCountDto mbtiStatistic(ReqMbtiStatisticDto dto);

}
