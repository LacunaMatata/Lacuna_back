package LacunaMatata.Lacuna.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCount {
    private int userCountId;
    private String serviceName;
    private int serviceCount;
    private LocalDateTime userDate;
    private int userHour;
}
