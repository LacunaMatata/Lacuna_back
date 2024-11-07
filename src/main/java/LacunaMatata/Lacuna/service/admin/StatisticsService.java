package LacunaMatata.Lacuna.service.admin;

import LacunaMatata.Lacuna.dto.request.admin.mbti.ReqMbtiStatisticDto;
import LacunaMatata.Lacuna.dto.response.admin.statistic.RespMbtiStatisticCountDto;
import LacunaMatata.Lacuna.dto.response.admin.statistic.RespMbtiStatisticDto;
import LacunaMatata.Lacuna.repository.admin.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    public RespMbtiStatisticCountDto MbtiStatisticDto(ReqMbtiStatisticDto dto) {

        RespMbtiStatisticCountDto respMbtiStatisticCountDto = null;

        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            respMbtiStatisticCountDto = statisticsMapper.mbtiStatistic(dto);
        }

        return respMbtiStatisticCountDto;
    }

}
