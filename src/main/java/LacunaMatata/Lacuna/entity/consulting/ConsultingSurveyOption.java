package LacunaMatata.Lacuna.entity.consulting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultingSurveyOption {
    private int consultingOptionId;
    private int consultingId;
    private String consultingOptionType;
    private String optionValue;
    private int optionScore;
}
