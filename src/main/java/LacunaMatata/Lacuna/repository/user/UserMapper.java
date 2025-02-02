package LacunaMatata.Lacuna.repository.user;

import LacunaMatata.Lacuna.entity.Setting;
import LacunaMatata.Lacuna.entity.mbti.MbtiResult;
import LacunaMatata.Lacuna.entity.order.Order;
import LacunaMatata.Lacuna.entity.user.*;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    /** auth 관련 mapper*/
    // 1-1.회원가입
    int saveUser(User user);
    // 1-2. 회원가입
    int saveUserOptionalInfo(UserOptionalInfo userOptionalInfo);
    // 1-3. 회원가입 - 권한 기본값 저장(member)_2024.11.01
    int saveUserRoleMet(Map<String, Object> params);
    // 1-4. 회원가입(오어스) - oauth 저장_2024.11.01
    int saveOauthInfo(SocialLogin socialLogin);
    // 1-5. 회원가입(오어스) - oauth로 회원가입 하지 않았지만 일반 회원가입으로 가입한 이메일이 oauth 계정일 때 socialLogin 수정
    int modifySocialLoginType(int userId);
    // 2-1. 로그인
    int saveLoginHistory(LoginHistory loginHistory);
    // 2-2. 로그인
    int modifyLoginDate(int userId);
    // 3. 프로필 페이지 - 프로필 이미지 변경
    int modifyMyProfileImg(Map<String, Object> params);
    // 4-2. 마이페이지 - 비밀번호 변경하기(User)_2024.11.05
    int modifyPassword(int userId, String modifyPassword);
    // 4-3. 마이페이지 - 비밀번호 변경하기(PasswordHistory)_2024.11.05
    int savePasswordHistory(PasswordHistory passwordHistory);
    // 5. 마이페이지 - 폰 번호 변경하기
    int modifyPhoneNumber(Map<String, Object> params);
    // 6. 마이페이지 - 이메일 변경(수정)
    int modifyMyEmail(Map<String, Object> params);
    // 7. 마이페이지 - 마케팅 정보 변경
    int changeMarketingAgreement(Map<String, Object> params);
    // 8-1. 마이페이지 - 회원 탈퇴(User)_2024.11.05
    int deleteUser(int userId);
    // 8-2. 마이페이지 - 회원 탈퇴(UserOptionalInfo)_2024.11.05
    int deleteUserOptionalInfo(int userId);
    // 8-3. 마이페이지 - 회원 탈퇴(UserRoleMet)_2024.11.05
    int deleteUserRoleMet(int userId);
    // 8-4. 마이페이지 - 회원 탈퇴(SocialLogin)_2024.11.05
    int deleteOauthInfo(int userId);
    // 9. 마이페이지 - MBTI 결과_2024.11.05
    MbtiResult getMyMbtiResult(int usertId);
    // 10. 마이페이지 - 주문 정보
    List<Order> getMyOrderInfo(Map<String, Object> params);
    // 11. 마이페이지 - 결제 취소 공동
    int cancelSystemPay(int orderId);
    // 12. 마이페이지 - 주문 취소 (계좌이체)
    int cancelMyOrder(int orderId);
    // 13-1. 이메일 인증번호 정보 저장_2024.12.04
    int saveEmailAuthentication(Map<String, Object> params);
    // 13-2. 이메일로 인증번호 찾기_2024.12.04
    EmailAuthentication findAuthenticationCodeByEmail(String email);
    // 13-3. 이메일 인증 통과시 isVerified 수정_2024.12.04
    int modifyEmailVerified(String email);
    // 13-4. 이메일 인증 데이터 삭제(회원가입 완료시)_2024.12.04
    int deleteEmailAuthentication(String email);

    /** 공통으로 사용할 userMapper */
    // 1. userId로 User 정보 찾기
    User findUserByUserId(int userId);
    // 2. username으로 User 정보 찾기
    User findUserByUsername(String username);
    // 3. email로 User 정보 찾기
    User findUserByEmail(String email);
    // 4. roleId로 UserRole 정보 찾기
    UserRole findUserRoleByRoleId(int roleId);
    // 5. userId로 로그인 기록 정보 찾기
    List<LoginHistory> findLoginHistoryByUserId(int userId);
    // 6. oAuth2Name(고유값)으로 사용자 찾기
    User findUserBySocialId(String socialId);
    // 7. 관리자 카카오 주소(문의용) 가져오기
    String getKakaoAddress();
    // 8. 주문 번호로 지불 정보 찾기
    int findPaymentByOrderId(int orderId);
    // 9-1. 아이디 찾기시 - 이메일, 이름, 생년월일로 user 정보 찾기
    User findUserByNameEmailBirth(Map<String, Object> params);
    // 9-2. 아이디 찾기시 - 관리자 이메일 정보 불러오기
    List<Setting> getAdminEmailAndPhone();
    // 10-1. 비밀번호 찾기시 - 계정Id, 이메일로 user 정보 찾기
    User findUserByUsernameEmail(Map<String, Object> params);
    // 10-2. 비밀번호 찾기시 - 인증코드 저장
    int updateAuthenticationCode(Map<String, Object> params);
    // 10-3. 비밀번호 찾기시 - 인증코드 비교
    User checkAuthenticationCode(String username);
    // 10-4. 비밀번호 찾기시 - 새 비밀번호 바꾸기
    int modifyNewPassword(Map<String, Object> params);
    // 11. 관리자 이용약관, 마케팅 등 정보 가져오기
    List<Setting> getAgreementInfoList();
    // 12. userId로 oauth 정보 찾아오기
    SocialLogin findSocialInfoByUserId(int userId);
}
