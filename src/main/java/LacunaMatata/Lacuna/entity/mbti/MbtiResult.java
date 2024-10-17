package LacunaMatata.Lacuna.entity.mbti;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MbtiResult {
    private int mbtiResultId;
    private int mbtiResultRegisterId;
    private String mbtiResultCategoryName;
    private String mbtiResultTitle;
    private String mbtiResultSummary;
    private String mbtiResultContent;
    private String mbtiResultImg;
    private int mbtiResultStatus;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
