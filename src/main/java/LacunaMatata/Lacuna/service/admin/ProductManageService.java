package LacunaMatata.Lacuna.service.admin;


import LacunaMatata.Lacuna.dto.request.admin.product.*;
import LacunaMatata.Lacuna.dto.response.admin.product.*;
import LacunaMatata.Lacuna.entity.product.*;
import LacunaMatata.Lacuna.repository.admin.ProductManageMapper;
import LacunaMatata.Lacuna.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductManageService {

    @Autowired
    private ProductManageMapper productManageMapper;

    @Value("${file.path}")
    private String filePath;

    // 상품 상위 분류 리스트 출력
    public RespCountAndUpperProductDto getProductUpperCategory() {
        List<ProductUpperCategory> productUpperCategoryList = productManageMapper.getProductUpperCategoryList();
        List<RespUpperProductCategoryListDto> productUpperCategory = new ArrayList<RespUpperProductCategoryListDto>();
        for(ProductUpperCategory productUpperCategoryDto : productUpperCategoryList) {
            RespUpperProductCategoryListDto respUpperProductCategoryListDto = RespUpperProductCategoryListDto.builder()
                    .productUpperCategoryId(productUpperCategoryDto.getProductUpperCategoryId())
                    .productUpperCategoryName(productUpperCategoryDto.getProductUpperCategoryName())
                    .name(productUpperCategoryDto.getUser().getName())
                    .createdDate(productUpperCategoryDto.getCreateDate())
                    .build();
            productUpperCategory.add(respUpperProductCategoryListDto);
        }
        int totalCount = productUpperCategoryList.isEmpty() ? 0 : productUpperCategoryList.get(0).getTotalCount();
        RespCountAndUpperProductDto respCountAndUpperProductDto = RespCountAndUpperProductDto.builder()
                .totalCount(totalCount)
                .productUpperCategoryList(productUpperCategory)
                .build();

        return respCountAndUpperProductDto;
    }

    // 상품 상위 분류 카테고리 등록
    public void registProductUpperCategory(ReqRegistUpperProductCategoryDto dto) {

        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int registerId = principalUser.getId();
        ProductUpperCategory productUpperCategory = ProductUpperCategory.builder()
                .productUpperCategoryName(dto.getProductUpperCategoryName())
                .productUpperCategoryRegisterId(registerId)
                .build();

        productManageMapper.saveProductUpperCategory(productUpperCategory);
    }

    // 상품 상위 분류 카테고리 항목 출력
    public RespUpperProductCategoryDto getProductUpper(int upperId) {
        ProductUpperCategory respUpperCategory = productManageMapper.getproductUpperDto(upperId);
        RespUpperProductCategoryDto upperCategory = RespUpperProductCategoryDto.builder()
                .productUpperCategoryId(respUpperCategory.getProductUpperCategoryId())
                .productUpperCategoryName(respUpperCategory.getProductUpperCategoryName())
                .build();
        return upperCategory;
    }

    // 상품 상위 분류 카테고리 수정
    public void modifyProductUpperCategory(ReqModifyUpperProductDto dto, int upperId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int registerId = principalUser.getId();
        ProductUpperCategory productUpperCategory = ProductUpperCategory.builder()
                .productUpperCategoryId(dto.getProductUpperCategoryId())
                .productUpperCategoryName(dto.getProductUpperCategoryName())
                .productUpperCategoryRegisterId(registerId)
                .build();

        productManageMapper.modifyProductUpperCategory(productUpperCategory);
    }

    // 상품 상위 분류 카테고리 삭제
    public void deleteProductUpperCategory(int upperId) {
        productManageMapper.deleteUpperProductCategory(upperId);
    }

    // 상품 상위 분류 카테고리 복수개 삭제
    public void deleteProductUpperCategoryList(ReqDeleteUpperProductCategoryListDto dto) {
        List<Integer> upperIdList = dto.getUpperCategoryIdList();
        productManageMapper.deleteUpperProductCategoryList(upperIdList);
    }

    // 상품 하위 분류 리스트 출력
    public RespCountAndLowerProductDto getProductlowerCategory(int upperId) {
        List<ProductLowerCategory> productLowerCategoryList = productManageMapper.getProductLowerCategoryList(upperId);
        List<RespLowerProductCategoryListDto> productLowerCategory = new ArrayList<RespLowerProductCategoryListDto>();
        for(ProductLowerCategory productLowerCategoryDto : productLowerCategoryList) {
            RespLowerProductCategoryListDto respLowerProductCategoryListDto = RespLowerProductCategoryListDto.builder()
                    .productLowerCategoryId(productLowerCategoryDto.getProductLowerCategoryId())
                    .productLowerCategoryName(productLowerCategoryDto.getProductLowerCategoryName())
                    .name(productLowerCategoryDto.getUser().getName())
                    .createdDate(productLowerCategoryDto.getCreateDate())
                    .build();
            productLowerCategory.add(respLowerProductCategoryListDto);
        }
        int totalCount = productLowerCategoryList.isEmpty() ? 0 : productLowerCategoryList.get(0).getTotalCount();

        RespCountAndLowerProductDto respCountAndLowerProductDto = RespCountAndLowerProductDto.builder()
                .totalCount(totalCount)
                .productLowerCategoryList(productLowerCategory)
                .build();

        return respCountAndLowerProductDto;
    }

    //상품 하위 분류 등록 모달창 출력
    public List<RespUpperProductCategoryDto> lowerRegistCategoryModal() {
        List<ProductUpperCategory> productUpperCategoryList = productManageMapper.getProductUpperCategoryList();
        List<RespUpperProductCategoryDto> respProductUpperCategoryList = new ArrayList<>();
        for (ProductUpperCategory productUpperCategory : productUpperCategoryList) {
            RespUpperProductCategoryDto respUpperProductCategoryDto = RespUpperProductCategoryDto.builder()
                    .productUpperCategoryId(productUpperCategory.getProductUpperCategoryId())
                    .productUpperCategoryName(productUpperCategory.getProductUpperCategoryName())
                    .build();
            respProductUpperCategoryList.add(respUpperProductCategoryDto);
        }
        return respProductUpperCategoryList;
    }

    // 상품 하위 분류 카테고리 등록
    public void registProductlowerCategory(ReqRegistLowerProductCategoryDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int registerId = principalUser.getId();
        ProductLowerCategory productLowerCategory = ProductLowerCategory.builder()
                .productLowerCategoryName(dto.getProductLowerCategoryName())
                .productLowerCategoryRegisterId(registerId)
                .productUpperCategoryId(dto.getProductUpperCategoryId())
                .build();

        productManageMapper.saveProductLowerCategory(productLowerCategory);
    }

    // 상품 하위 분류 카테고리 항목 출력
    public RespLowerProductCategoryDto getProductLower(int lowerId) {
        ProductLowerCategory respLowerCategory = productManageMapper.getProductLowerDto(lowerId);
        RespLowerProductCategoryDto lowerCategory = RespLowerProductCategoryDto.builder()
                .productLowerCategoryId(respLowerCategory.getProductLowerCategoryId())
                .productLowerCategoryName(respLowerCategory.getProductLowerCategoryName())
                .build();
        return lowerCategory;
    }

    // 상품 하위 분류 카테고리 수정
    public void modifyProductlowerCategory(ReqModifyLowerProductCategoryDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int registerId = principalUser.getId();
        ProductLowerCategory productLowerCategory = ProductLowerCategory.builder()
                .productLowerCategoryId(dto.getProductLowerCategoryId())
                .productLowerCategoryName(dto.getProductLowerCategoryName())
                .productLowerCategoryRegisterId(registerId)
                .build();

        productManageMapper.modifyProductLowerCategory(productLowerCategory);
    }

    // 상품 하위 분류 카테고리 삭제
    public void deleteProductlowerCategory(int lowerId) {
        productManageMapper.deleteProductLowerCategory(lowerId);
    }

    // 상품 하위 분류 카테고리 복수개 삭제
    public void deleteProductlowerCategoryList(ReqDeleteLowerProductCategoryListDto dto) {
        List<Integer> lowerIdList = dto.getLowerCategoryIdList();
        productManageMapper.deleteProductLowerCategoryList(lowerIdList);
    }

    // 상품 리스트 출력
    public RespCountAndProductDto getProducts(ReqGetProductListDto dto) {
        int startIndex = (dto.getPage() - 1) * dto.getLimit();
        Map<String, Object> params = Map.of(
                "filter", dto.getFilter(),
                "option", dto.getOption(),
                "searchValue", dto.getSearchValue() == null ? "" : dto.getSearchValue(),
                "startIndex", startIndex,
                "limit", dto.getLimit()
        );
        List<Product> productList = productManageMapper.getProductList(params);
        System.out.println(productList);
        List<RespProductListDto> respProductListDtoList = new ArrayList<RespProductListDto>();
        for(Product product : productList) {
            RespProductListDto respProductListDto = RespProductListDto.builder()
                    .productId(product.getProductId())
                    .productCode(product.getProductCode())
                    .productUpperCategoryName(product.getProductUpperCategory().getProductUpperCategoryName())
                    .productName(product.getProductName())
                    .price(product.getPrice())
                    .promotionPrice(product.getPromotionPrice())
                    .name(product.getUser().getName())
                    .createdDate(product.getCreateDate())
                    .build();
            respProductListDtoList.add(respProductListDto);
        }
        int totalCount = productList.isEmpty() ? 0 : productList.get(0).getTotalCount();

        RespCountAndProductDto respCountAndProductDto = RespCountAndProductDto.builder()
                .totalCount(totalCount)
                .productList(respProductListDtoList)
                .build();

        return respCountAndProductDto;
    }

    // 상품 등록 모달창 출력
    public RespProductRegistModalDto getRegistModal() {
        List<RespUpperProductCategoryAndLowerDto> productUpperAndLower = productManageMapper.getProductUpperAndLowerCategoryList();
//        List<ConsultingContent> consultingContent = productManageMapper.getConsultingContent();
        RespProductRegistModalDto respProductRegistModalDto = RespProductRegistModalDto.builder()
                .respUpperProductCategoryAndLowerDto(productUpperAndLower)
//                .productConsultingContentList(consultingContent)
                .build();
        return respProductRegistModalDto;
    }

    // 상품 등록
    @Transactional(rollbackFor = Exception.class)
    public void registProduct(ReqRegistProductDto dto) throws Exception {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int registeId = principalUser.getId();

        // 이미지 등록
        // 1. 이미지 신규 등록할 공간 생성
        List<MultipartFile> insertImgs = dto.getInsertImgs();
        String insertCompletedImgPath = null;

        // 2. 신규 이미지 저장
        if(insertImgs != null && !insertImgs.get(0).isEmpty()) {
            insertCompletedImgPath = registerImgUrl(insertImgs.get(0), "product");
        }

        Product product = Product.builder()
<<<<<<< HEAD
                .productProductUpperCategoryId(dto.getProductProductUpperCategoryId())
=======
                .productProductUpperCategoryId(dto.getProductUpperCategoryId())
>>>>>>> 71c51673d40f970459e025ac21320c5251a1f061
                .productLowerCategoryId(dto.getProductLowerCategoryId())
                .productCode(dto.getProductCode())
                .productName(dto.getProductName())
                .price(BigDecimal.valueOf(dto.getPrice()))
                .promotionPrice(BigDecimal.valueOf(dto.getPromotionPrice()))
                .productImg(insertCompletedImgPath)
                .productRegisterId(registeId)
                .description(dto.getDescription())
                .etc(dto.getEtc())
                .build();

        productManageMapper.saveProduct(product);

        // Todo 상품 세부 정보 컨설팅 분류에 맞게 넣는거 알아서 짜봐여. (dto, enttiy 확인 및 수정 필요
        // Todo insert 된 id는 useGenerator 사용하면 build한 엔티티 변수에 들어있는 것을 사용하면 됨 (위 이미지 넣은 방법 참조)
        switch (dto.getProductProductUpperCategoryId()) {
            case 1:
                ConsultingDetail consultingDetail = ConsultingDetail.builder()
                        .consultingDetailProductId(product.getProductId())
                        .consultingDetailContentId(dto.getConsultingContentId())
                        .repeatCount(dto.getRepeatCount())
                        .build();
                productManageMapper.saveConsultingDetail(consultingDetail);
                break;
            case 2:
                CosmeticDetail cosmeticDetail = CosmeticDetail.builder()
                        .cosmeticDetailProductId(product.getProductId())
                        .volume(dto.getVolume())
                        .ingredient(dto.getIngredient())
                        .skinType(dto.getSkinType())
                        .effect(dto.getEffect())
                        .manufacture(dto.getManufacture())
                        .productUrl(dto.getProductUrl())
                        .build();
                productManageMapper.saveCosmeticDetail(cosmeticDetail);
                break;
        }
    }

    // 상품 항목 출력(수정 모달창) -> ProductDetailAspect 로 넘겨줌
    public RespProductDetailDto getProductDetail(int productId) {
        Product product = productManageMapper.getProduct(productId);

        RespProductDetailDto productConsultingDetail = RespProductDetailDto.builder()
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
                .build();
        return productConsultingDetail;
    }

    // 상품 수정
    @Transactional(rollbackFor = Exception.class)
    public void modifyProduct(ReqModifyProductDto dto) throws IOException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int registerId = principalUser.getId();

        /* 이미지 삭제 후 이미지 추가 */
        // 단계 : 1. 신규 등록, 삭제 공간 생성, 2. 이미지 경로 DB 삭제 및 DB 파일 삭제 3. 신규 데이터 등록

        // 1. 최종 수정될 imgPath 공간 생성
        String finalImgPath = dto.getPrevImgPath();

        // 2. 이미지 신규 등록할 공간 생성
        List<MultipartFile> insertImgs = dto.getInsertImgs();

        // 3. 이미지 삭제할 공간 생성
        String deleteImgPath = dto.getDeleteImgPath();

        // 4. 물리 파일 삭제
        if(deleteImgPath != null) {
            deleteImgUrl(deleteImgPath);
            finalImgPath = null;
        }

        // 이미지 등록
        // 1. 이미지 수정할 공간 생성
        if(insertImgs != null && insertImgs.get(0).isEmpty()) {
            finalImgPath = registerImgUrl(insertImgs.get(0), "product");
        }

        Product product = Product.builder()
                .productId(dto.getProductId())
<<<<<<< HEAD
                .productProductUpperCategoryId(dto.getProductProductUpperCategoryId())
=======
                .productProductUpperCategoryId(dto.getProductUpperCategoryId())
>>>>>>> 71c51673d40f970459e025ac21320c5251a1f061
                .productLowerCategoryId(dto.getProductLowerCategoryId())
                .productCode(dto.getProductCode())
                .productName(dto.getProductName())
                .price(BigDecimal.valueOf(dto.getPrice()))
                .promotionPrice(BigDecimal.valueOf(dto.getPromotionPrice()))
                .productImg(finalImgPath)
                .productRegisterId(registerId)
                .description(dto.getDescription())
                .etc(dto.getEtc())
                .build();
        productManageMapper.modifyProduct(product);

        if(product.getProductProductUpperCategoryId() == 1) {
            ConsultingDetail consultingDetail = ConsultingDetail.builder()
                    .consultingDetailProductId(dto.getConsultingDetailProductId())
                    .consultingDetailContentId(dto.getConsultingContentId())
                    .repeatCount(dto.getRepeatCount())
                    .build();
            productManageMapper.modifyConsultingDetail(consultingDetail);
        }

        if(product.getProductProductUpperCategoryId() == 2) {
            CosmeticDetail cosmeticDetail = CosmeticDetail.builder()
                    .cosmeticDetailId(dto.getCosmeticDetailId())
                    .cosmeticDetailProductId(dto.getCosmeticDetailProductId())
                    .volume(dto.getVolume())
                    .ingredient(dto.getIngredient())
                    .skinType(dto.getSkinType())
                    .effect(dto.getEffect())
                    .manufacture(dto.getManufacture())
                    .productUrl(dto.getProductUrl())
                    .build();
            productManageMapper.modifyCosmeticDetail(cosmeticDetail);
        }
    }

    // 상품 삭제
    public void deleteProduct(int productId) {
        productManageMapper.deleteProduct(productId);
    }

    // 상품 복수개 삭제
    public void deleteProductList(ReqDeleteProductDto dto) {
        List<Integer> productIdList =  dto.getProductIdList();
        productManageMapper.deleteProductList(productIdList);
    }

    // 상품 상위 카테고리 분류 출력(필터용)
    public List<RespUpperProductCategoryDto> getUpperProductFilter() {
        List<ProductUpperCategory> upperFilter = productManageMapper.getProductUpperCategoryList();
        List<RespUpperProductCategoryDto> upperFilterList = new ArrayList<RespUpperProductCategoryDto>();
        for(ProductUpperCategory productUpperCategory : upperFilter) {
            RespUpperProductCategoryDto upperFilter2 = RespUpperProductCategoryDto.builder()
                    .productUpperCategoryId(productUpperCategory.getProductUpperCategoryId())
                    .productUpperCategoryName(productUpperCategory.getProductUpperCategoryName())
                    .build();
            upperFilterList.add(upperFilter2);
        }
        return upperFilterList;
    }

    // 상품 하위 카테고리 분류 출력(필터용)
    public List<RespLowerProductCategoryDto> getLowerProductFilter(int upperId) {
        List<ProductLowerCategory> lowerFilter = productManageMapper.getProductLowerCategoryList(upperId);
        List<RespLowerProductCategoryDto> lowerFilterList = new ArrayList<RespLowerProductCategoryDto>();
        for(ProductLowerCategory productLowerCategory : lowerFilter) {
            RespLowerProductCategoryDto lowerFilter2 = RespLowerProductCategoryDto.builder()
                    .productLowerCategoryId(productLowerCategory.getProductLowerCategoryId())
                    .productLowerCategoryName(productLowerCategory.getProductLowerCategoryName())
                    .build();
            lowerFilterList.add(lowerFilter2);
        }
        return lowerFilterList;
    }

    public String registerImgUrl(MultipartFile img, String dirName ) throws IOException {
        String imgName = img.getOriginalFilename();
        // Todo 디렉토리 경로 잘 확인해서 넣어야 함
        File directory = new File(filePath + dirName);
        if(!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(filePath + dirName + imgName);
        img.transferTo(file);

        return filePath + dirName + imgName;
    }

    public void deleteImgUrl(String imgUrl) {
        File file = new File(imgUrl);
        if(file.exists()) {
            file.delete();
        }
    }
}
