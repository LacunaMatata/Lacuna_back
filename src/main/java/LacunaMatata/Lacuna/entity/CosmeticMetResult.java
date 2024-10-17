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
public class CosmeticMetResult {
    private int cosmeticMetResultId;
    private int cosmeticResultConsultingUpperCategoryId;
    private int cosmeticMetResultScore;
    private int consultingResponseId;
    private String cosmeticMetReason;
    private int cosmeticResultRegisterId;
    private int mbtiResultStatus;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
