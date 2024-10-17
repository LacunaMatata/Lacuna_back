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
public class Setting {
    private int settingId;
    private String dateType;
    private String value;
    private int settingRegisterId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
