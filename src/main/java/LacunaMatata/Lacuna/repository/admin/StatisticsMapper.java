package LacunaMatata.Lacuna.repository.admin;

import LacunaMatata.Lacuna.dto.request.admin.mbti.ReqMbtiStatisticDto;
import LacunaMatata.Lacuna.dto.response.admin.statistic.RespMbtiStatisticCountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StatisticsMapper {
    RespMbtiStatisticCountDto mbtiStatistic(ReqMbtiStatisticDto dto);
}
