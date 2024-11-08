package LacunaMatata.Lacuna.dto.response.admin.statistic;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespMbtiStatisticCountDto {
    private Integer DRNT;
    private Integer DRNW;
    private Integer DRPT;
    private Integer DRPW;
    private Integer DSNT;
    private Integer DSNW;
    private Integer DSPT;
    private Integer DSPW;
    private Integer ORNT;
    private Integer ORNW;
    private Integer ORPT;
    private Integer ORPW;
    private Integer OSNT;
    private Integer OSNW;
    private Integer OSPT;
    private Integer OSPW;
}
