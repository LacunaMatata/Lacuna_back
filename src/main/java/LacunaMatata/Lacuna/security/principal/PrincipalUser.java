package LacunaMatata.Lacuna.security.principal;

import LacunaMatata.Lacuna.entity.user.UserRoleMet;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/************************************
 * version: 1.0.2                   *
 * author: 손경태                    *
 * description: PrincipalUser       *
 * createDate: 2024-10-10           *
 * updateDate: 2024-10-10           *
 ***********************************/
@Data
@Builder
public class PrincipalUser implements UserDetails {
    private int id;
    private String username;
    private String password;
    private Set<UserRoleMet> userRoleMets;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoleMets.stream().map(ur -> new SimpleGrantedAuthority(
                ur.getUserRole().getRoleName())).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
