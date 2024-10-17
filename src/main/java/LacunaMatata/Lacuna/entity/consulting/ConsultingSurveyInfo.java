package LacunaMatata.Lacuna.entity.consulting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultingSurveyInfo {
    private int consultingId;
    private int consultingLowerCategoryId;
    private int consultingSurveyRegisterId;
    private String consultingTitle;
    private String consultingSubtitle;
    private String consultingImg;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
