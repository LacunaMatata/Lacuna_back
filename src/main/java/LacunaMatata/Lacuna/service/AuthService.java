package LacunaMatata.Lacuna.service;

import LacunaMatata.Lacuna.dto.request.user.auth.*;
import LacunaMatata.Lacuna.entity.user.*;
import LacunaMatata.Lacuna.exception.InactiveAccountException;
import LacunaMatata.Lacuna.repository.user.UserMapper;
import LacunaMatata.Lacuna.security.ip.IpUtils;
import LacunaMatata.Lacuna.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Comparator;

/************************************
 * version: 1.0.5                   *
 * author: 손경태                    *
 * description: AuthService         *
 * createDate: 2024-10-17           *
 * updateDate: 2024-10-21           *
 ***********************************/
@Service
public class AuthService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired private IpUtils ipUtils;

    @Autowired private JwtProvider jwtProvider;

    @Autowired private UserMapper userMapper;

    // 일반 회원 가입
    public void signup(ReqGeneralSignupDto dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .name(dto.getName())
                .build();
        int userId = userMapper.saveUser(user);

        UserOptionalInfo userOptionalInfo = UserOptionalInfo.builder()
                .userId(userId)
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .marketingReceiveAgreement(dto.getMarketingReceiveAgreement())
                .thirdPartyInfoSharingAgreement(dto.getThirdPartyInfoSharingAgreement())
                .useConditionAgreement(dto.getUseConditionAgreement())
                .build();
        userMapper.saveUserOptionalInfo(userOptionalInfo);

        for(int i = 1; i < 3; i++) {
            UserRoleMet userRoleMet = UserRoleMet.builder()
                    .roleUserId(userId)
                    .roleId(i)
                    .build();
            userMapper.saveUserRoleMet(userRoleMet);
        }
    }

    // 일반 로그인
    public String signin(HttpServletRequest request, ReqGeneralSigninDto dto) {
        User user = userMapper.findUserByUsername(dto.getUsername());

        // 계정 비활성화인 경우
        if(user.getLastLoginDate().isBefore(LocalDateTime.now().minusYears(1))) {
            throw new InactiveAccountException();
        }

        // 토큰 가져오기
        int roleId = user.getUserRoleMets().stream().map(ur -> ur.getRoleId())
                .max(Comparator.naturalOrder()).orElse(2);
        String roleName = userMapper.findUserRoleByRoleId(roleId).getRoleName();

        String accessToken = jwtProvider.generateAccessToken(user.getUserId(), roleName);

        // ip 가져오기
        String loginIp = IpUtils.getClientIp(request);
        // 로그인 정보에 로그인 시간과 ip 저장하기
        LoginHistory loginHistory = LoginHistory.builder()
                .loginUserId(user.getUserId())
                .loginIp(loginIp)
                .build();
        userMapper.saveLoginHistory(loginHistory);

        return accessToken;
    }

    public void oauthSignup(ReqOauthSignupDto dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .name(dto.getName())
                .build();
        int userId = userMapper.saveUser(user);

        UserOptionalInfo userOptionalInfo = UserOptionalInfo.builder()
                .userId(userId)
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .marketingReceiveAgreement(dto.getMarketingReceiveAgreement())
                .thirdPartyInfoSharingAgreement(dto.getThirdPartyInfoSharingAgreement())
                .useConditionAgreement(dto.getUseConditionAgreement())
                .build();
        userMapper.saveUserOptionalInfo(userOptionalInfo);

        SocialLogin socialLogin = SocialLogin.builder()
                .socialUserId(userId)
                .socialId(dto.getSocialId())
                .provider(dto.getProvider())
                .build();
        userMapper.saveOauthInfo(socialLogin);

        for(int i = 1; i < 3; i++) {
            UserRoleMet userRoleMet = UserRoleMet.builder()
                    .roleUserId(userId)
                    .roleId(i)
                    .build();
            userMapper.saveUserRoleMet(userRoleMet);
        }
    }

    // username이 있는 지 검사 -> AuthAspect로 들어감
    public Boolean isNonUserByUsername(String username) {
        User user = userMapper.findUserByUsername(username);
        if(user == null) {
            return true;
        }
        return false;
    }

    // username의 비밃번호를 틀렸을 때 검사 -> AuthAspect로 들어감
    public Boolean isDifferentPassword(ReqGeneralSigninDto dto) {
        User user = userMapper.findUserByUsername(dto.getUsername());

        if(!user.getPassword().equals(dto.getPassword())) {
            return true;
        }
        return false;
    }

    // username 중복 되는 지 검사 -> AuthAspect로 들어감
    public Boolean isDuplicateUsername(String username) {
        User user = userMapper.findUserByUsername(username);

        if(user == null) {
            return true;
        }
        return false;
    }

    // email 중복되는지 검사 -> AuthAspect로 들어감
    public Boolean isDuplicateEmail(String email) {
        User user = userMapper.findUserByEmail(email);

        if(user == null) {
            return true;
        }
        return false;
    }

    public Boolean sendAuthEmail(ReqAuthEmailDto dto) {
        String toEmail = dto.getToEmail();

        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<div style='display:flex;justify-content:center;align-items:center;flex-direction:column;"
         + "width:400px'>");
        htmlContent.append("<h2>Lacuna 회원가입 이메일 인증 입니다.</h2>");
        htmlContent.append("<h3>아래 인증하기 버튼을 클릭해주세요</h3>");
        htmlContent.append("<a target='_blank' href='http://localhost:8080/api/v1/auth/email?emailtoken=");
        htmlContent.append(jwtProvider.generateEmailValidToken(toEmail));
        htmlContent.append("'>인증하기</a>");
        htmlContent.append("</div>");

        return  send(toEmail, "Lacuna 회원가입 이메일 인증 ", htmlContent.toString());
    }

    public Boolean send(String toEmail, String subject, String htmlContent) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            message.setText(htmlContent, "utf-8", "html");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        javaMailSender.send(message);
        return true;
    }

    public String validToken(String emailValidtoken) throws Exception {
        try {
            // 만료시간이 지나면 못 꺼낼 것임 -> 지나명 validFail 리턴
            jwtProvider.getClaim(emailValidtoken);

            // 시간이 유효하면 success 리턴
            return "success";
        } catch (Exception e) {
            return "validFail";
        }
    }

    public void findUsername(ReqFindUsernameDto dto) throws Exception {
        String toEmail = dto.getToEmail();

        User user = userMapper.findUserByEmail(toEmail);
        if(user == null) {
            throw new Exception("없는 계정");
        }

        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<div style='display:flex;justify-content:center;align-items:center;flex-direction:column;"
                + "width:400px'>");
        htmlContent.append("<h2>Lacuna 사용자 계정찾기 안내</h2>");
        htmlContent.append("<h3>회원님의 계정 아이디는");
        htmlContent.append(user.getUsername());
        htmlContent.append("입니다.</h3>");
        htmlContent.append("</div>");

        send(toEmail, "Lacuna 아이디 찾기", htmlContent.toString());
    }

    public void findPassword(ReqFindPasswordDto dto) throws Exception {
        User user = userMapper.findUserByUsername(dto.getUsername());
        if(user == null) {
            throw new Exception("없는 계정");
        }

        String toEmail = dto.getToEmail();
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<div style='display:flex;justify-content:center;align-items:center;flex-direction:column;"
                + "width:400px'>");
        htmlContent.append("<h2>Lacuna 사용자 비밀번호 찾기 안내</h2>");
        htmlContent.append("<h3>회원님의 계정 비밀번호는");
        htmlContent.append(user.getPassword());
        htmlContent.append("입니다.</h3>");
        htmlContent.append("</div>");

        send(toEmail, "Lacuna 비밀번호 찾기", htmlContent.toString());
    }
}
