package LacunaMatata.Lacuna.entity.mbti;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MbtiOption {
    private int mbtiOptionId;
    private int mbtiId;
    private String optionName;
    private int optionScore;
}
