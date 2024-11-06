package LacunaMatata.Lacuna.dto.request.admin.product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.List;

@Data
public class ReqRegistProductDto {
    private int productProductUpperCategoryId;
    private int productLowerCategoryId;
    private String productCode;
    private String productName;
    private int price;
    private int promotionPrice;
    private String description;
    private String etc;

    // 상위 분류가 컨설팅일때 받을 곳
    private int consultingContentId; // 컨설팅 상품 컨텐츠 ID
    private int repeatCount; // 컨설팅 반복 횟수

    // 상위 분류가 화장품일 때 받을 곳
    private String volume; // 화장품 상품 용량
    private String ingredient; // 화장품 상품 성분
    private String skinType; // 화장품 상품 피부타입
    private String effect; // 화장품 상품 효능
    private String manufacture; // 화장품 상품 제조사
    private String productUrl; // 화장품 상품 url

    // 상품 이미지 파일을 받는 곳
    private List<MultipartFile> insertImgs;
}
