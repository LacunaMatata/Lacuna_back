package LacunaMatata.Lacuna.repository.admin;

import LacunaMatata.Lacuna.dto.response.admin.product.RespUpperProductCategoryAndLowerDto;
import LacunaMatata.Lacuna.entity.product.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductManageMapper {

    /** 상품 상위 분류 관련 Mapper */
    // 1. 상품 상위 분류 조회_2024.10.26 // 14. 상품 상위 분류 항목 출력(필터)
    List<ProductUpperCategory> getProductUpperCategoryList();
    // 2. 상품 상위 분류 등록_2024.10.26
    int saveProductUpperCategory(ProductUpperCategory productUpperCategory);
    // 3. 상품 상위 분류 모달 조회_2024.10.26
    ProductUpperCategory getproductUpperDto(int upperId);
    // 4. 상품 상위 분류 모달 수정_2024.10.26
    int modifyProductUpperCategory(ProductUpperCategory productUpperCategory);
    // 5. 상품 상위 분류 단일 삭제_2024.10.26
    int deleteUpperProductCategory(int upperId);
    // 6. 상품 상위 분류 복수 삭제_2024.10.26
    int deleteUpperProductCategoryList(List<Integer> upperIdList);

    /** 상품 하위 분류 관련 Mapper */
    // 1. 상품 상위분류에 해당하는 하위분류 카테고리 출력_2024.10.26
    List<ProductLowerCategory> getProductLowerCategoryList(int upperId);
    // 2. 상품 하위 분류 등록_2024.10.26
    int saveProductLowerCategory(ProductLowerCategory productLowerCategory);
    // 3. 상품 하위 분류 모달 조회_2024.10.26
    ProductLowerCategory getProductLowerDto(int lowerId);
    // 4. 상품 하위 분류 모달 수정_2024.10.26
    int modifyProductLowerCategory(ProductLowerCategory productLowerCategory);
    // 5. 상품 하위 분류 단일 삭제_2024.10.26
    int deleteProductLowerCategory(int lowerId);
    // 6. 상품 하위 분류 복수 삭제_2024.10.26
    int deleteProductLowerCategoryList(List<Integer> lowerIdList);
    // 7. 상위 분류 항목과 하위 분류 항목 리스트 출력
    List<RespUpperProductCategoryAndLowerDto> getProductUpperAndLowerCategoryList();
    // 8. 컨설팅 상품의 콘텐츠 내용 리스트 출력
    List<ConsultingContent> getConsultingContent();

    /** 상품 관련 Mapper */
    // 1. 상품 리스트 출력_2024.10.26
    List<Product> getProductList(Map<String,Object> params);
    // 2. 상품 등록_2024.10.26
    int saveProduct(Product product);
    // 3. 상위카테고리가 컨설팅인 상품일 때 컨설팅 Content 등록_2024.10.26
    int saveConsultingContent(ConsultingContent consultingContent);
    // 4. 상위카테고리가 컨설팅인 상품일 때 컨설팅 Detail 등록_2024.10.26
    int saveConsultingDetail(ConsultingDetail consultingDetail);
    // 5. 상위카테고리가 화장품인 상품일 때 화장품 Detail 등록_2024.10.26
    int saveCosmeticDetail(CosmeticDetail cosmeticDetail);
    // 6. 상품 모잘창 출력(상위 카테고리 컨설팅, 화장품 둘다 해당)_2024.10.26
    Product getProduct(int productId);
    // 7. 상위카테고리가 컨설팅인 상품일 때 컨설팅 모달창 출력_2024.10.26
    ConsultingDetail getConsultingDetail(int productId);
    // 8. 상위카테고리가 화장품인 상품일 때 화장품 모달창_2024.10.26
    CosmeticDetail getCosmeticDetail(int productId);
    // 9. 상품 모달창 수정(상위 카테고리 컨설팅, 화장품 둘다 해당)_2024.10.26
    int modifyProduct(Product product);
    // 10-1. 상품 상위카테고리가 컨설팅인 상품일 때 컨설팅 모달창 수정_2024.10.26
    int modifyConsultingDetail(ConsultingDetail consultingDetail);
//    // 10-2. 상품 상위카테고리가 컨설팅인 상품일 때 컨설팅 모달창 수정_2024.10.26 - 삭제
//    int modifyConsultingContent(ConsultingContent consultingContent);
    // 11. 상품 상위카테고리가 화장품인 상품일 때 화장품 모달창_2024.10.26
    int modifyCosmeticDetail(CosmeticDetail cosmeticDetail);
    // 12. 상품 단일 삭제_2024.10.26
    int deleteProduct(int productId); // 상품 삭제
    // 13. 상품 복수 삭제_2024.10.26
    int deleteProductList(List<Integer> productIdList);
//    // 14. 상품 이미지 등록 - 삭제
//    int insertProductImg(String insertCompletedImgPath, int productId);

    /** 상품 관련 공통으로 필요한 메서드 Mapper */
    // 1. 상품 상위 분류 이름으로 상품 상위 분류 정보 찾기_2024.10.26
    ProductUpperCategory findByNameProductUpperCategory(String upperCategoryName);
    // 2. 상품 하위 분류 이름으로 상품 하위 분류 정보 찾기__2024.10.26
    ProductLowerCategory findByNameProductLowerCategory(String lowerCategoryName);
    // 3. 상품 ID로 상품 상위 분류가 컨설팅인 정보 찾기_2024.10.26
    ConsultingDetail findByIdConsultingDetail(int productId);
}
