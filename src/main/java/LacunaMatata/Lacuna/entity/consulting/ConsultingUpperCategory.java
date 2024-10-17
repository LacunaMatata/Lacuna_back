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
public class ConsultingUpperCategory {
    private int ConsultingUpperCategoryId;
    private String ConsultingUpperCategoryName;
    private String ConsultingUpperCategoryDescription;
    private String ConsultingUpperCategoryImg;
    private int consultingUpperCategoryRegisterId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
