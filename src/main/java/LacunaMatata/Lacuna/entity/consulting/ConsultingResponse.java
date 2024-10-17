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
public class ConsultingResponse {
    private int consultingResponseId;
    private int consultingResponseUserId;
    private int consultingResponseUpperCategoryId;
    private int responseConsultingId;
    private int consultingOptionId;
    private int consultingResponseStatus;
    private LocalDateTime createDate;
}
