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
public class PasswordHistory {
    private int historyId;
    private int historyUserId;
    private String password;
    private LocalDateTime changedDate;
}
