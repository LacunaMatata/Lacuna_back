package LacunaMatata.Lacuna.entity.consulting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultingResponseImg {
    private int consultingResponseImgId;
    private int consultingResponseImgConsultingResponseId;
    private String imgPath;
}
