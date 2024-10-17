package LacunaMatata.Lacuna.service;

import LacunaMatata.Lacuna.entity.user.User;
import LacunaMatata.Lacuna.repository.UserMapper;
import LacunaMatata.Lacuna.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/************************************
 * version: 1.0.3                   *
 * author: 손경태                    *
 * description: TokenService        *
 * createDate: 2024-10-11           *
 * updateDate: 2024-10-11           *
 ***********************************/
@Service
public class TokenService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtProvider jwtProvider;

    public void isValidToken(String bearerAccessToken) {
        try {
            String accessToken = jwtProvider.removeBearer(bearerAccessToken);

            Claims claims = jwtProvider.getClaim(accessToken);
            int userId = ((Integer) claims.get("userId")).intValue();
            User user = userMapper.findByUserId(userId);

            if(user == null) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("유효성 발생");
        }
    }
}
