package LacunaMatata.Lacuna.repository.user;

import LacunaMatata.Lacuna.dto.response.user.purchase.consultingProductDetail.RespConsultingProductDetailDto;
import LacunaMatata.Lacuna.dto.response.user.purchase.consultingProductList.RespProductUpperCategoryDto;
import LacunaMatata.Lacuna.entity.order.Order;
import LacunaMatata.Lacuna.entity.order.OrderItem;
import LacunaMatata.Lacuna.entity.order.Payment;
import LacunaMatata.Lacuna.entity.product.Product;
import LacunaMatata.Lacuna.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PurchaseMapper {
    /** 회원 - 컨설팅 상품 구매Mapper */
    // 1. 컨설팅 상품 목록 불러오기_2024.11.06
    RespProductUpperCategoryDto getConsultingProductList();
    // 2. 컨설팅 상품 항목 보기_2024.11.06
    RespConsultingProductDetailDto getConsultingProductDetail(int productId);
    // 3-1. 컨설팅 상품 주문하기(구매하기 버튼 클릭) - order_2024.11.06
    int saveOrder(Order order);
    // 3-2. 컨설팅 상품 주문하기(구매하기 버튼 클릭) - orderItem
    int saveOrderItem(List<OrderItem> orderItemList);
    // 3-3. 컨설팅 상품 지불 데이터 추가
    int savePayment(Payment payment);
    // 4. 컨설팅 상품 구매 (계좌 이체)_2024.11.11
    int savePaymentAccount();

    /** 공통으로 사용할 Mapper */
    // 1. 상품 ID로 상품 찾기
    List<Product> findProductByProductId(List<Integer> productIdList);
    // 2-1. 권한 수정용_user 권한 정보 찾기
    User findUserByUserId(int userId);
    // 2-2. 권한 증가
    int saveUserRoleMet(Map<String, Object> params);
    // 2-3. 권한 수정
    int modifyUserRoleMet(Map<String, Object> params);
}
