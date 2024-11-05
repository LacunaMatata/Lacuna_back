package LacunaMatata.Lacuna.controller.admin;

import LacunaMatata.Lacuna.aspect.annotation.Log;
import LacunaMatata.Lacuna.aspect.annotation.admin.ProductDetailAop;
import LacunaMatata.Lacuna.dto.request.admin.product.*;
import LacunaMatata.Lacuna.dto.response.admin.product.*;
import LacunaMatata.Lacuna.service.admin.ProductManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/************************************************
 * version: 1.0.4                               *
 * author: 손경태                                *
 * description: AdminProductController() - 세팅  *
 * createDate: 2024-10-16                       *
 * updateDate: 2024-10-16                       *
 ************************************************/
@RestController
@Slf4j
@Api(tags = "ProductManageController")
@RequestMapping("/api/v1/admin/product")
public class ProductManageController {

    @Autowired
    private ProductManageService productManageService;

    // 상품 상위 분류 리스트 출력 - 완료
    @GetMapping("/upper/list")
    @ApiOperation(value = "getUpperProductListApi")
    public ResponseEntity<?> getUpperProductList() {
        RespCountAndUpperProductDto respCountAndUpperProductDto =
                productManageService.getProductUpperCategory();
        return ResponseEntity.ok().body(respCountAndUpperProductDto);
    }

    // 상품 상위 분류 카테고리 등록
    @PostMapping("/upper/regist")
    @ApiOperation(value = "registUpperProductApi")
    public ResponseEntity<?> registUpperProduct(@RequestBody ReqRegistUpperProductCategoryDto dto) {
        productManageService.registProductUpperCategory(dto);
        return ResponseEntity.ok().body(true);
    }

    // 상품 상위 분류 항목 출력
    @GetMapping("/upper/{upperId}")
    @ApiOperation(value = "getUpperProductApi")
    public ResponseEntity<?> getUpperProduct(@PathVariable int upperId) {
        RespUpperProductCategoryDto respUpperCategory = productManageService.getProductUpper(upperId);
        return ResponseEntity.ok().body(respUpperCategory);
    }

    // 상품 상위 분류 카테고리 수정
    @PutMapping("/upper/modify/{upperId}")
    @ApiOperation(value = "modifyUpperProductApi")
    public ResponseEntity<?> modifyUpperProduct(@RequestBody ReqModifyUpperProductDto dto, @PathVariable int upperId) {
        productManageService.modifyProductUpperCategory(dto, upperId);
        return ResponseEntity.ok().body(true);
    }

    // 상품 상위 분류 카테고리 삭제
    @DeleteMapping("/upper/delete/{upperId}")
    @ApiOperation(value = "deleteUpperProductApi")
    public ResponseEntity<?> deleteUpperProduct(@PathVariable int upperId) {
        productManageService.deleteProductUpperCategory(upperId);
        return ResponseEntity.ok().body(true);
    }

    // 상품 상위 분류 카테고리 복수개 삭제
    @DeleteMapping("/upper/delete")
    @ApiOperation(value = "deleteUpperProductListApi")
    public ResponseEntity<?> deleteUpperProductList(@RequestBody ReqDeleteUpperProductCategoryListDto dto) {
        productManageService.deleteProductUpperCategoryList(dto);
        return ResponseEntity.ok().body(true);
    }

    // 상품 하위 분류 리스트 출력
    @GetMapping("/lower/list/{upperId}")
    @ApiOperation(value = "getLowerProductListApi")
    public ResponseEntity<?> getLowerProductList(@PathVariable int upperId) {
        RespCountAndLowerProductDto respCountAndLowerProductDto =
                productManageService.getProductlowerCategory(upperId);
        return ResponseEntity.ok().body(respCountAndLowerProductDto);
    }

    // 상품 하위 분류 등록 모달창 출력
    @GetMapping("/lower/regist/modal")
    @ApiOperation(value = "getLowerCategiryRegistModalApi")
    public ResponseEntity<?> getLowerCategiryRegistModal() {
        List<RespUpperProductCategoryDto> productUpperCategoryList = productManageService.lowerRegistCategoryModal();
        return ResponseEntity.ok().body(productUpperCategoryList);
    }

    // 상품 하위 분류 카테고리 등록
    @PostMapping("/lower/regist")
    @ApiOperation(value = "registLowerProductApi")
    public ResponseEntity<?> registLowerProduct(@RequestBody ReqRegistLowerProductCategoryDto dto) {
        System.out.println("요청 들어옴?" + dto);
        productManageService.registProductlowerCategory(dto);
        return ResponseEntity.ok().body(true);
    }

    // 상품 하위 분류 카테고리 수정 모달창 출력
    @GetMapping("/lower/{lowerId}")
    @ApiOperation(value = "getLowerProductApi")
    public ResponseEntity<?> getLowerProduct(@PathVariable int lowerId) {
        RespLowerCategoryModifyModalDto respLowerCategory = productManageService.getProductLower(lowerId);
        return ResponseEntity.ok().body(respLowerCategory);
    }

    // 상품 하위 분류 카테고리 수정
    @PutMapping("/lower/modify/{lowerId}")
    @ApiOperation(value = "modifyLowerProductApi")
    public ResponseEntity<?> modifyLowerProduct(@RequestBody ReqModifyLowerProductCategoryDto dto, @PathVariable int lowerId) {
        productManageService.modifyProductlowerCategory(dto);
        return ResponseEntity.ok().body(true);
    }

    // 상품 하위 분류 카테고리 삭제
    @DeleteMapping("/lower/delete/{lowerId}")
    @ApiOperation(value = "deleteLowerProductApi")
    public ResponseEntity<?> deleteLowerProduct(@PathVariable int lowerId) {
        productManageService.deleteProductlowerCategory(lowerId);
        return ResponseEntity.ok().body(true);
    }

    // 상품 하위 분류 카테고리 복수개 삭제
    @DeleteMapping("/lower/delete")
    @ApiOperation(value = "deleteLowerProductListApi")
    public ResponseEntity<?> deleteLowerProductList(@RequestBody ReqDeleteLowerProductCategoryListDto dto) {
        productManageService.deleteProductlowerCategoryList(dto);
        return ResponseEntity.ok().body(true);
    }

    // 상품 리스트 출력
    @GetMapping("/list")
    @ApiOperation(value = "getProductListApi")
    public ResponseEntity<?> getProductList(ReqGetProductListDto dto) {
        RespCountAndProductDto respCountAndProductDto = productManageService.getProducts(dto);
    return ResponseEntity.ok().body(respCountAndProductDto);
    }

    // 상품 등록 모달창 출력
    @GetMapping("/regist/modal")
    @ApiOperation(value = "getRegistModalApi")
    public ResponseEntity<?> getRegistModal() {
        return ResponseEntity.ok().body(productManageService.getRegistModal());
    }

    // 상품 등록
    @PostMapping("/regist")
    @Log
    @ApiOperation(value = "registProductApi")
    public ResponseEntity<?> registProduct(@ModelAttribute ReqRegistProductDto dto) throws Exception {
        System.out.println("상품 데이터 요청"+ dto);
        productManageService.registProduct(dto);
        return ResponseEntity.ok().body(true);
    }

    // 상품 수정 모달창 출력
    @ProductDetailAop
    @GetMapping("/{productId}")
    @ApiOperation(value = "getProductApi")
    public ResponseEntity<?> getProduct(@PathVariable int productId) {
        return ResponseEntity.ok().body(productManageService.getProductDetail(productId));
    }

    // 상품 수정
    @PutMapping("/modify/{productId}")
    @ApiOperation(value = "modifyProductApi")
    public ResponseEntity<?> modifyProduct(@RequestBody ReqModifyProductDto dto, @PathVariable int productId) throws IOException {
        productManageService.modifyProduct(dto);
        return ResponseEntity.ok().body(true);
    }

    // 상품 삭제
    @DeleteMapping("/delete/{productId}")
    @ApiOperation(value = "deleteProductApi")
    public ResponseEntity<?> deleteProduct(@PathVariable int productId) {
        productManageService.deleteProduct(productId);
        return ResponseEntity.ok().body(true);
    }

    // 상품 복수개 삭제
    @DeleteMapping("/delete")
    @ApiOperation(value = "deleteProductListApi")
    public ResponseEntity<?> deleteProductList(@RequestBody ReqDeleteProductDto dto) {
        productManageService.deleteProductList(dto);
        return ResponseEntity.ok().body(true);
    }

    // 상품 상위 분류 출력(필터용)
    @GetMapping("/upper/filter")
    public ResponseEntity<?> getUpperProductFilter() {
        List<RespUpperProductCategoryDto> upperFilter = productManageService.getUpperProductFilter();
        return ResponseEntity.ok().body(upperFilter);
    }

    // 상품 하위 분류 출력(필터용)
    @GetMapping("/lower/filter/{upperId}")
    public ResponseEntity<?> getLowerProductFilter(@PathVariable int upperId) {
        List<RespLowerProductCategoryDto> lowerFilter = productManageService.getLowerProductFilter(upperId);
        return ResponseEntity.ok().body(lowerFilter);
    }
}
