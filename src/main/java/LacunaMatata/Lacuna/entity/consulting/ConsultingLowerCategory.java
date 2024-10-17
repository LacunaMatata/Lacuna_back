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
public class ConsultingLowerCategory {
    private int ConsultingLowerCategoryId;
    private int ConsultingUpperCategoryId;
    private String ConsultingLowerCategoryName;
    private String ConsultingLowerCategoryDescription;
    private int consultingLowerCategoryRegisterId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
