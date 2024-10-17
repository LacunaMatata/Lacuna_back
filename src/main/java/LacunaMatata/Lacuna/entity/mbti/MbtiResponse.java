package LacunaMatata.Lacuna.entity.mbti;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MbtiResponse {
    private int mbtiResponseId;
    private int mbtiRequestUserId;
    private String sessionId;
    private int gender;
    private Date birth;
    private int skinProblemOne;
    private int skinProblemTwo;
    private int responseMbtiCategoryId;
    private int mbtiResultId;
    private int responseMbtiId;
    private int mbtiOptionId;
    private LocalDateTime createDate;
}
