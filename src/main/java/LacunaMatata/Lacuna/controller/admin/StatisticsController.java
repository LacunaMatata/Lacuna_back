package LacunaMatata.Lacuna.controller.admin;

import LacunaMatata.Lacuna.dto.request.admin.statistic.ReqMbtiStatisticDto;
import LacunaMatata.Lacuna.service.admin.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/statistics")
@Api(tags = {"statisticsController"})
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @PostMapping("/")
    public ResponseEntity<?> registCounting() {return ResponseEntity.ok().body(null);
    }

    @ApiOperation(value = "mbtiStatisticApi")
    @GetMapping("/mbti")
    public ResponseEntity<?> mbtiStatistic(ReqMbtiStatisticDto dto) {
        System.out.println("통계 요청 들어옴?"+dto);
        return ResponseEntity.ok().body(statisticsService.MbtiStatisticDto(dto));
    }
}
