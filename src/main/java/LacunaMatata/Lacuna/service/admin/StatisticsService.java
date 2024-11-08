package LacunaMatata.Lacuna.service.admin;

import LacunaMatata.Lacuna.dto.request.admin.statistic.ReqMbtiStatisticDto;
import LacunaMatata.Lacuna.dto.response.admin.statistic.RespUserStatisticCountDto;
import LacunaMatata.Lacuna.repository.admin.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    public RespUserStatisticCountDto MbtiStatisticDto(ReqMbtiStatisticDto dto) {

        RespUserStatisticCountDto respUserStatisticCountDto = null;

        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            respUserStatisticCountDto = statisticsMapper.userStatistic(dto);
        }

        return respUserStatisticCountDto;
    }

}
