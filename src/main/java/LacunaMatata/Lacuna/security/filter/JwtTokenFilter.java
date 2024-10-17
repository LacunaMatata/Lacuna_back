package LacunaMatata.Lacuna.security.filter;


import LacunaMatata.Lacuna.entity.user.User;
import LacunaMatata.Lacuna.repository.UserMapper;
import LacunaMatata.Lacuna.security.jwt.JwtProvider;
import LacunaMatata.Lacuna.security.principal.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/************************************
 * version: 1.0.2                   *
 * author: 손경태                    *
 * description: JwtTokenFilter      *
 * createDate: 2024-10-10           *
 * updateDate: 2024-10-10           *
 ***********************************/
@Component
public class JwtTokenFilter extends GenericFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String bearerAccessToken = httpServletRequest.getHeader("Authorization");

        if(bearerAccessToken == null || bearerAccessToken.isBlank()) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String accessToken = jwtProvider.removeBearer(bearerAccessToken);

        try {
            Claims claims = jwtProvider.getClaim(accessToken);
            int userId = ((Integer) claims.get("userId")).intValue();
            User user = userMapper.findByUserId(userId);
            if(user == null) {
                throw new JwtException("해당 id의 사용자를 찾지 못했습니다");
            }

            PrincipalUser principalUser = user.toPrincipal();

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch(JwtException e) {
            e.printStackTrace();
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
