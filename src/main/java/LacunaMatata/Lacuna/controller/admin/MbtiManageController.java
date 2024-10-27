package LacunaMatata.Lacuna.controller.admin;

import LacunaMatata.Lacuna.dto.request.admin.mbti.*;
import LacunaMatata.Lacuna.dto.response.admin.mbti.RespMbtiCategoryDto;
import LacunaMatata.Lacuna.dto.response.admin.mbti.RespMbtiCategoryListDto;
import LacunaMatata.Lacuna.dto.response.admin.mbti.RespMbtiQuestionListDto;
import LacunaMatata.Lacuna.service.admin.MbtiManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/************************************************
 * version: 1.0.4                               *
 * author: 손경태                                *
 * description: AdminMbtiController() - 세팅     *
 * createDate: 2024-10-16                       *
 * updateDate: 2024-10-27                       *
 ************************************************/
@RestController
@RequestMapping("/api/v1/admin/mbti")
@Api(tags = {"mbtiManageController"})
public class MbtiManageController {

    @Autowired
    private MbtiManageService mbtiManageService;

    // MBTI 분류 카테고리 리스트 출력
    @ApiOperation(value = "getMbtiCategoryListApi")
    @GetMapping("/servey/category/list")
    public ResponseEntity<?> getMbtiCategoryList(@RequestBody ReqGetMbtiGategoryListDto dto) {
        List<RespMbtiCategoryListDto> mbtiCategoryList = mbtiManageService.getMbtiCategoryList(dto);
        return ResponseEntity.ok().body(mbtiCategoryList);
    }

    // MBTI 분류 카테고리 등록
    @ApiOperation(value = "registerMbtiCategoryApi")
    @PostMapping("/servey/category/register")
    public ResponseEntity<?> registerMbtiCategory(@RequestBody ReqRegisterMbtiCategoryDto dto) {
        mbtiManageService.registerMbtiCategory(dto);
        return ResponseEntity.ok().body(null);
    }

    //MBTI 분류 카테고리 모달 조회
    @ApiOperation(value = "getMbtiCategoryApi")
    @GetMapping("/servey/category/{categoryId}")
    public ResponseEntity<?> getMbtiCategory(@PathVariable int categoryId) {
        RespMbtiCategoryDto mbtiCategory = mbtiManageService.getMbtiCategory(categoryId);
        return ResponseEntity.ok().body(mbtiCategory);
    }

    // MBTI 분류 카테고리 모달 수정
    @ApiOperation(value = "modifyMbtiCategoryApi")
    @PutMapping("/servey/category/modify")
    public ResponseEntity<?> modifyMbtiCategory(@RequestBody ReqModifyMbtiCategoryDto dto) {
        mbtiManageService.modifyMbtiCategory(dto);
        return ResponseEntity.ok().body(true);
    }

    // MBTI 분류 카테고리 삭제
    @ApiOperation(value = "deleteMbtiCategoryApi")
    @DeleteMapping("/servey/category/delete/{categoryId}")
    public ResponseEntity<?> deleteMbtiCategory(@PathVariable int categoryId) {
        mbtiManageService.deleteMbtiCategory(categoryId);
        return ResponseEntity.ok().body(null);
    }

    // MBTI 분류 카테고리 복수개 삭제
    @ApiOperation(value = "deleteMbtiCategoryListApi")
    @DeleteMapping("/servey/category/delete")
    public ResponseEntity<?> deleteMbtiCategoryList(@RequestBody ReqDeleteMbtiCategoryListDto dto) {
        mbtiManageService.deleteMbtiCategoryList(dto);
        return ResponseEntity.ok().body(null);
    }

    // MBTI 설문 항목 리스트 출력
    @ApiOperation(value = "getMbtiQuestionListApi")
    @GetMapping("/question")
    public ResponseEntity<?> getMbtiQuestionList(@RequestBody ReqGetMbtiQuestionListDto dto) {
        List<RespMbtiQuestionListDto> mbtiQuestionList = mbtiManageService.getMbtiQuestionList(dto);
        return ResponseEntity.ok().body(mbtiQuestionList);
    }

    // MBTI 설문 항목 등록
    @ApiOperation(value = "registerMbtiQuestionApi")
    @PostMapping("/question/register")
    public ResponseEntity<?> registerMbtiQuestion(@RequestBody ReqRegisterMbtiQuestionDto dto) {
//        mbtiManageService.registerMbtiQuestion();
        return ResponseEntity.ok().body(null);
    }

    // MBTI 설문 항목 수정
    @ApiOperation(value = "modifyMbtiQuestionApi")
    @PutMapping("/question/modify")
    public ResponseEntity<?> modifyMbtiQuestion(@RequestBody ReqModifyMbtiQuestionDto dto) {
//        mbtiManageService.modifyMbtiQuestion();
        return ResponseEntity.ok().body(null);
    }

    // MBTI 설문 항목 삭제
    @ApiOperation(value = "deleteMbtiQuestionApi")
    @DeleteMapping("/question/delete/{mbtiId}")
    public ResponseEntity<?> deleteMbtiQuestion(@PathVariable int mbtiId) {
        mbtiManageService.deleteMbtiQuestion(mbtiId);
        return ResponseEntity.ok().body(null);
    }

    // MBTI 설문 항목 복수개 삭제
    @ApiOperation(value = "deleteMbtiQuestionListApi")
    @DeleteMapping("/question/delete")
    public ResponseEntity<?> deleteMbtiQuestionList() {
        return ResponseEntity.ok().body(null);
    }

    // MBTI 설문 결과 리스트 출력
    @ApiOperation(value = "getMbtiResultListApi")
    @GetMapping("/result/list")
    public ResponseEntity<?> getMbtiResultList() {
        return ResponseEntity.ok().body(null);
    }

    // MBTI 설문 결과 등록
    @ApiOperation(value = "registerMbtiResultApi")
    @PostMapping("/result/register")
    public ResponseEntity<?> registerMbtiResult(@RequestBody ReqRegisterMbtiResultDto dto) {
        mbtiManageService.registerMbtiResult(dto);
        return ResponseEntity.ok().body(true);
    }

    // MBTI 설문 결과 수정
    @ApiOperation(value = "modifyMbtiResultApi")
    @PutMapping("/result/modify/{resultId}")
    public ResponseEntity<?> modifyMbtiResult(@RequestBody ReqModifyMbtiResultDto dto) {
        mbtiManageService.modifyMbtiResult(dto);
        return ResponseEntity.ok().body(true);
    }

    // MBTI 설문 결과 삭제
    @ApiOperation(value = "deleteMbtiResultApi")
    @DeleteMapping("/result/delete/{resultId}")
    public ResponseEntity<?> deleteMbtiResult(@PathVariable int resultId) {
        mbtiManageService.deleteMbtiResult(resultId);
        return ResponseEntity.ok().body(true);
    }

    // MBTI 설문 결과 복수개 삭제
    @ApiOperation(value = "deleteMbtiResultListApi")
    @DeleteMapping("/result/delete")
    public ResponseEntity<?> deleteMbtiResultList() {
        return ResponseEntity.ok().body(null);
    }
}
