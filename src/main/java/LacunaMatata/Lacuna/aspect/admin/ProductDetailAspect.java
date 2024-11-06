package LacunaMatata.Lacuna.aspect.admin;

import LacunaMatata.Lacuna.dto.response.admin.product.*;
import LacunaMatata.Lacuna.entity.product.ConsultingContent;
import LacunaMatata.Lacuna.entity.product.ConsultingDetail;
import LacunaMatata.Lacuna.entity.product.CosmeticDetail;
import LacunaMatata.Lacuna.entity.product.Product;
import LacunaMatata.Lacuna.repository.admin.ProductManageMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class ProductDetailAspect {

    @Autowired
    private ProductManageMapper productManageMapper;

    @Pointcut("@annotation(LacunaMatata.Lacuna.aspect.annotation.admin.ProductDetailAop)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object after(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // ResponseEntity를 반환하고 그 내부의 Body를 캐스팅
        ResponseEntity<?> responseEntity = (ResponseEntity<?>) proceedingJoinPoint.proceed();
        Object body = responseEntity.getBody();

        int productId = (int) proceedingJoinPoint.getArgs()[0];

        List<RespUpperProductCategoryAndLowerDto> productUpperAndLower = productManageMapper.getProductUpperAndLowerCategoryList();

        List<ConsultingContent> consultingContent = productManageMapper.getConsultingContent();
        RespProductRegistModalDto respProductRegistModalDto = RespProductRegistModalDto.builder()
                .respUpperProductCategoryAndLowerDto(productUpperAndLower)
                .productConsultingContentList(consultingContent)
                .build();

        if (body instanceof RespProductDetailDto) {

            Product product = productManageMapper.getProduct(productId);
            System.out.println(product);
            // 만약 컨설팅 관련 상품이면 컨설팅 내용만 담아서 보냄
            if(product.getProductUpperCategory().getProductUpperCategoryId() == 1) {
                ConsultingDetail consultingDetail = productManageMapper.getConsultingDetail(productId);
                RespConsultingDetailDto respConsultingDetailDto = RespConsultingDetailDto.builder()
                        .productId(product.getProductId())
                        .productUpperCategoryId(product.getProductUpperCategory().getProductUpperCategoryId())
                        .productUpperCategoryName(product.getProductUpperCategory().getProductUpperCategoryName())
                        .productLowerCategoryName(product.getProductLowerCategory().getProductLowerCategoryName())
                        .productCode(product.getProductCode())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .promotionPrice(product.getPromotionPrice())
                        .productImg(product.getProductImg())
                        .description(product.getDescription())
                        .etc(product.getEtc())
                        .repeatCount(consultingDetail.getRepeatCount())
                        .consultingDetailContentId(consultingDetail.getConsultingDetailContentId())
                        .consultingName(consultingDetail.getConsultingContent().getName())
                        .build();
                RespModifyConsultingProductModalDto modifyConsultingProductDto = RespModifyConsultingProductModalDto.builder()
                        .respProductRegistModalDto(respProductRegistModalDto)
                        .respConsultingDetailDto(respConsultingDetailDto)
                        .build();
                return ResponseEntity.ok().body(modifyConsultingProductDto);
            }

            // 만약 화장품 관련 상품이면 화장품 내용만 담아서 보냄
            if(product.getProductUpperCategory().getProductUpperCategoryId() == 2) {
                CosmeticDetail cosmeticDetail = productManageMapper.getCosmeticDetail(productId);
                System.out.println(cosmeticDetail);
                RespCosmeticDetailDto respCosmeticDetailDto = RespCosmeticDetailDto.builder()
                        .productId(product.getProductId())
                        .productUpperCategoryId(product.getProductUpperCategory().getProductUpperCategoryId())
                        .productUpperCategoryName(product.getProductUpperCategory().getProductUpperCategoryName())
                        .productLowerCategoryName(product.getProductLowerCategory().getProductLowerCategoryName())
                        .productCode(product.getProductCode())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .promotionPrice(product.getPromotionPrice())
                        .productImg(product.getProductImg())
                        .description(product.getDescription())
                        .etc(product.getEtc())
                        .volume(cosmeticDetail.getVolume())
                        .ingredient(cosmeticDetail.getIngredient())
                        .skinType(cosmeticDetail.getSkinType())
                        .effect(cosmeticDetail.getEffect())
                        .manufacture(cosmeticDetail.getManufacture())
                        .productUrl(cosmeticDetail.getProductUrl())
                        .build();
                RespModifyCosmeticProductDto respModifyCosmeticProductDto = RespModifyCosmeticProductDto.builder()
                        .respProductRegistModalDto(respProductRegistModalDto)
                        .respCosmeticDetailDto(respCosmeticDetailDto)
                        .build();
                return ResponseEntity.ok().body(respModifyCosmeticProductDto);
            }
        }

        // Body가 예상한 타입이 아닐 경우 원래의 ResponseEntity 반환
        return responseEntity;
    }
}
