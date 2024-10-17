package LacunaMatata.Lacuna.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/************************************
 * version: 1.0.2                   *
 * author: 손경태                    *
 * description: UserRole            *
 * createDate: 2024-10-10           *
 * updateDate: 2024-10-10           *
 ***********************************/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRole {
    private int roleId;
    private String roleName;
    private String roleDescription;
    private String roleImg;
}
