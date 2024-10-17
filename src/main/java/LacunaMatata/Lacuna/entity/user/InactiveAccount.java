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
public class InactiveAccount {
    private int activityUserId;
    private LocalDateTime lastActiveDate;
    private int inactiveFlag;
}
