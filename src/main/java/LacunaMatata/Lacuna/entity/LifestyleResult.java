package LacunaMatata.Lacuna.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LifestyleResult {
    private int lifestyleResultId;
    private int lifestyleResultConsultingUpperCategoryId;
    private String lifestyleCategoryResultType;
    private String lifestyleResultTitle;
    private String lifestyleResultContent;
    private int lifestyleResultRegisterId;
    private int mbtiResultStatus;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
