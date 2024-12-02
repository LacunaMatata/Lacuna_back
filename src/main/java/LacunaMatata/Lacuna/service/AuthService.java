package LacunaMatata.Lacuna.service;

import LacunaMatata.Lacuna.dto.request.user.auth.*;
import LacunaMatata.Lacuna.entity.user.*;
import LacunaMatata.Lacuna.exception.auth.*;
import LacunaMatata.Lacuna.repository.user.UserMapper;
import LacunaMatata.Lacuna.security.ip.IpUtils;
import LacunaMatata.Lacuna.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

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

    @Autowired private BCryptPasswordEncoder passwordEncoder;

    // 일반 회원 가입
    @Transactional(rollbackFor = Exception.class)
    public void signup(ReqGeneralSignupDto dto) throws Exception {

        try {
            User user = User.builder()
                    .username(dto.getUsername())
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .name(dto.getName())
                    .build();
            userMapper.saveUser(user);

            int useConditionAgreement = 0;
            int marketingReceiveAgreement = 0;
            int thirdPartyInfoSharingAgreement = 0;

            if(dto.getUseConditionAgreement() == true) {
                useConditionAgreement = 1;
            } else {
                useConditionAgreement = 2;
            }
            if(dto.getMarketingReceiveAgreement() == true) {
                marketingReceiveAgreement = 1;
            } else {
                marketingReceiveAgreement = 2;
            }
            if(dto.getThirdPartyInfoSharingAgreement() == true) {
                thirdPartyInfoSharingAgreement = 1;
            } else {
                thirdPartyInfoSharingAgreement = 2;
            }

            UserOptionalInfo userOptionalInfo = UserOptionalInfo.builder()
                    .userId(user.getUserId())
                    .birthDate(dto.getBirthDate())
                    .gender(dto.getGender())
                    .phoneNumber(dto.getPhoneNumber())
                    .address(dto.getAddress())
                    .marketingReceiveAgreement(marketingReceiveAgreement)
                    .thirdPartyInfoSharingAgreement(thirdPartyInfoSharingAgreement)
                    .useConditionAgreement(useConditionAgreement)
                    .build();
            userMapper.saveUserOptionalInfo(userOptionalInfo);

            List<Integer> roleIdList = new ArrayList<>();
            roleIdList.add(1);
            roleIdList.add(2);
            Map<String, Object> params = Map.of(
                    "userId", user.getUserId(),
                    "roleIdList", roleIdList
            );
            userMapper.saveUserRoleMet(params);
        } catch (Exception e) {
            throw new Exception("회원가입 도중 오류가 발생했습니다.");
        }
    }

    // 일반 로그인
    @Transactional(rollbackFor = Exception.class)
    public String signin(HttpServletRequest request, ReqGeneralSigninDto dto) throws Exception {
        User user = userMapper.findUserByUsername(dto.getUsername());

        // 계정 비활성화인 경우
        if(user.getLastLoginDate().isBefore(LocalDateTime.now().minusYears(1))) {
            throw new Exception("휴면계정입니다. 이메일 인증으로 휴면 계정을 복구하시고 이용해주세요.");
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

    // 오어스 회원가입
    @Transactional(rollbackFor = Exception.class)
    public void oauthSignup(ReqOauthSignupDto dto) throws Exception {

        try {
            User user = User.builder()
                    .username(dto.getUsername())
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .name(dto.getName())
                    .build();
            userMapper.saveUser(user);

            int useConditionAgreement = 0;
            int marketingReceiveAgreement = 0;
            int thirdPartyInfoSharingAgreement = 0;

            if(dto.getUseConditionAgreement() == true) {
                useConditionAgreement = 1;
            } else {
                useConditionAgreement = 2;
            }
            if(dto.getMarketingReceiveAgreement() == true) {
                marketingReceiveAgreement = 1;
            } else {
                marketingReceiveAgreement = 2;
            }
            if(dto.getThirdPartyInfoSharingAgreement() == true) {
                thirdPartyInfoSharingAgreement = 1;
            } else {
                thirdPartyInfoSharingAgreement = 2;
            }

            UserOptionalInfo userOptionalInfo = UserOptionalInfo.builder()
                    .userId(user.getUserId())
                    .birthDate(dto.getBirthDate())
                    .gender(dto.getGender())
                    .phoneNumber(dto.getPhoneNumber())
                    .address(dto.getAddress())
                    .marketingReceiveAgreement(marketingReceiveAgreement)
                    .thirdPartyInfoSharingAgreement(thirdPartyInfoSharingAgreement)
                    .useConditionAgreement(useConditionAgreement)
                    .build();
            userMapper.saveUserOptionalInfo(userOptionalInfo);

            SocialLogin socialLogin = SocialLogin.builder()
                    .socialUserId(user.getUserId())
                    .socialId(dto.getSocialId())
                    .provider(dto.getProvider())
                    .build();
            userMapper.saveOauthInfo(socialLogin);

            List<Integer> roleIdList = new ArrayList<>();
            roleIdList.add(1);
            roleIdList.add(2);
            Map<String, Object> params = Map.of(
                    "userId", user.getUserId(),
                    "roleIdList", roleIdList
            );
            userMapper.saveUserRoleMet(params);
        } catch (Exception e) {
            throw new Exception("회원가입 도중 오류가 발생하였습니다. 잠시 후 이용 부탁드립니다. (서버 오류)");
        }
    }

    // username 중복 되는 지 검사 -> AuthAspect로 들어감
    public Boolean isDuplicateUsername(String username) {
        User user = userMapper.findUserByUsername(username);

        if(user == null) {
            return true;
        }
        return false;
    }

    // username의 비밃번호를 틀렸을 때 검사 -> AuthAspect로 들어감
    public Boolean isDifferentPassword(ReqGeneralSigninDto dto) {
        User user = userMapper.findUserByUsername(dto.getUsername());

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
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

    // 아이디 찾기
    public String findUsername(ReqFindUsernameDto dto) throws EmailNotFoundException {
        String toEmail = dto.getEmail();
        Map<String, Object> params = Map.of(
            "email", toEmail,
            "birth", dto.getBirth(),
            "name", dto.getName()
        );

        User user = userMapper.findUserByNameEmailBirth(params);
        if(user == null) {
            throw new EmailNotFoundException("해당 이메일에 대한 정보가 존재하지 않습니다. 회원가입 때 입력한 이메일을 적어주세요.");
        }

        String username = user.getUsername();
        String maskingUsername = maskingInfo(username);

        return maskingUsername;
    }

    // 비밀번호 찾기1 - 인증코드 보내기
    @Transactional(rollbackFor = Exception.class)
    public void findPassword(ReqFindPasswordDto dto) throws UsernameNotFoundException {
        String toEmail = dto.getEmail();
        Map<String, Object> params = Map.of(
            "username", dto.getUsername(),
            "email", toEmail
        );
        User user = userMapper.findUserByUsernameEmail(params);
        if(user == null) {
            throw new UsernameNotFoundException("해당 계정의 정보를 찾을 수 없습니다. 입력하신 정보를 다시 한 번 확인하세요.");
        }

        StringBuilder code = generateAuthenticationCode();
        String authenticationCode = code.toString();
        Map<String, Object> params2 = Map.of(
            "userId", user.getUserId(),
            "authenticationCode", authenticationCode
        );
        userMapper.updateAuthenticationCode(params2);

        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<div style='display:flex;justify-content:center;align-items:center;flex-direction:column;"
                + "width:400px'>");
        htmlContent.append("<h2>Lacuna 사용자 인증코드 안내</h2>");
        htmlContent.append("<h3>회원님의 임시 인증코드는");
        htmlContent.append(authenticationCode);
        htmlContent.append("입니다.</h3>");
        htmlContent.append("</div>");

        send(toEmail, "Lacuna 비밀번호 찾기 인증코드", htmlContent.toString());
    }

    // 비밀번호 찾기2 - 인증코드 확인
    public Boolean checkAuthenticationCode(ReqCheckAuthenticationDto dto) {
        User user = userMapper.checkAuthenticationCode(dto.getUsername());
        if(user == null) {
            throw new UsernameNotFoundException("입력하신 계정의 정보가 없습니다. 다시 한 번 확인 부탁드립니다.");
        }

        if(!user.getAuthenticationCode().equals(dto.getAuthenticationCode())) {
            throw new NotMatchAuthenticationException("인증번호 불일치");
        }

        return true;
    }

    // 비밀번호 찾기3 - 새 비밀번호로 저장하기
    @Transactional(rollbackFor = Exception.class)
    public void changeNewPassword(ReqChangeNewPasswordDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        User user = userMapper.findUserByUsername(username);
        if(passwordEncoder.matches(password, user.getPassword())) {
            throw new IsPresentPasswordException("현재 사용하고 있는 비밀번호와 일치합니다. 새로운 비밀번호를 입력 바랍니다.");
        }

        if(!password.equals(dto.getPasswordCheck())) {
            throw new NotMatchPasswordCheckException("비밀번호가 일치하지 않습니다. 다시 확인 부탁드립니다.");
        }

        String encoingPassword = passwordEncoder.encode(password);

        Map<String, Object> params = Map.of(
            "username", username,
            "password", encoingPassword
        );
        userMapper.modifyNewPassword(params);
        PasswordHistory passwordHistory = PasswordHistory.builder()
                .historyUserId(user.getUserId())
                .password(encoingPassword)
                .build();
        userMapper.savePasswordHistory(passwordHistory);
    }

    private String maskingInfo(String info) {
        if (info == null || info.length() < 3) {
            // 너무 짧은 ID는 마스킹하지 않음
            return info;
        }

        int length = info.length();
        int start = length / 3; // ID 길이의 1/3 지점
        int end = start + 2;   // 마스킹할 2글자

        // ID가 짧으면 끝까지 마스킹
        if (end > length) {
            end = length;
        }

        // 마스킹 처리
        String maskedInfo = info.substring(0, start)
                + "*".repeat(end - start)
                + info.substring(end);

        return maskedInfo;
    }

    private StringBuilder generateAuthenticationCode() {
        // 임시 비밀번호 생성
        String tempCharacter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int passwordLength = 10;

        SecureRandom randomNumber = new SecureRandom();
        StringBuilder tempCode = new StringBuilder();

        for(int i = 0; i < passwordLength; i++) {
            int index = randomNumber.nextInt(tempCharacter.length());
            tempCode.append(tempCharacter.charAt(index));
        }

        return tempCode;
    }

    // 인증 성공 화면
    public String successView() {
        StringBuilder sb = new StringBuilder();

        sb.append("<html>");
        sb.append("<body>");
        sb.append("<script>");
        sb.append("alert('인증이 완료되었습니다');");
        sb.append("</script>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }

    // 인증 실패 화면
    public String errorView(String message) {
        StringBuilder sb = new StringBuilder();

        sb.append("<html>");
        sb.append("<body>");
        sb.append("<div style=\"text-align:center;\">");
        sb.append("<h2>");
        sb.append(message);
        sb.append("</h2>");
        // onclick 소문자로 해야함
        sb.append("<button onclick='window.close()'>닫기</button>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }
}
