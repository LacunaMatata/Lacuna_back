package LacunaMatata.Lacuna.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/************************************
 * version: 1.0.2                   *
 * author: 손경태                    *
 * description: userRoleMet         *
 * createDate: 2024-10-10           *
 * updateDate: 2024-10-10           *
 ***********************************/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleMet {
    private int userRoleMetId;
    private int roleUserId;   // userId
    private int roleId;       // foreign key from UserRole
    private UserRole userRole;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
