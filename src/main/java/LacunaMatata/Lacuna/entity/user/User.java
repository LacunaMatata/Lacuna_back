package LacunaMatata.Lacuna.entity.user;

import LacunaMatata.Lacuna.security.principal.PrincipalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/************************************
 * version: 1.0.2                   *
 * author: 손경태                    *
 * description: User                *
 * createDate: 2024-10-10           *
 * updateDate: 2024-10-10           *
 ***********************************/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String name;
    private int socialLoginType;
    private LocalDateTime passwordLastChanged;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private Set<UserRoleMet> userRoleMets;

    public PrincipalUser toPrincipal() {
        return PrincipalUser.builder()
                .id(userId)
                .username(username)
                .password(password)
                .userRoleMets(userRoleMets)
                .build();
    }
}
