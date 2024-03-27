package com.nocaffeine.ssgclone.like.presentation;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.like.application.LikeFolderService;
import com.nocaffeine.ssgclone.like.dto.LikeAddFolderDto;
import com.nocaffeine.ssgclone.like.dto.LikeFolderDto;
import com.nocaffeine.ssgclone.like.vo.request.LikeFolderAddRequestVo;
import com.nocaffeine.ssgclone.like.vo.request.LikeFolderModifyRequestVo;
import com.nocaffeine.ssgclone.like.vo.request.LikeFolderRemoveRequestVo;
import com.nocaffeine.ssgclone.like.vo.request.ProductLikeMoveRequestVo;
import com.nocaffeine.ssgclone.like.vo.response.LikeFolderListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/like/folder")
public class LikeFolderController {

    private final JwtTokenProvider jwtTokenProvider;
    private final LikeFolderService likeFolderService;

    @Operation(summary = "폴더 추가", description = "폴더 추가", tags = {"like folder add"})
    @PostMapping
    public CommonResponse<String> folderAdd(@RequestBody LikeFolderAddRequestVo likeFolderAddRequestVo){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        LikeFolderDto likeFolderDto = LikeFolderDto.voToDto(likeFolderAddRequestVo);
        likeFolderService.addLikeFolder(likeFolderDto, memberUuid);
        return CommonResponse.success("폴더 추가 성공");

    }

    @Operation(summary = "폴더 조회", description = "폴더 조회", tags = {"like folder find"})
    @GetMapping
    public CommonResponse<List<LikeFolderListResponseVo>> folderList(){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());

        List<LikeFolderDto> likeFolderListDto = likeFolderService.findLikeFolderList(memberUuid);

        List<LikeFolderListResponseVo> likeFolderListResponseVo = new ArrayList<>();
        for (LikeFolderDto likeFolderDto : likeFolderListDto) {
            likeFolderListResponseVo.add(LikeFolderListResponseVo.dtoToVo(likeFolderDto));
        }

        return CommonResponse.success("폴더 조회 성공",likeFolderListResponseVo);
    }

    @Operation(summary = "폴더 삭제", description = "폴더 삭제", tags = {"like folder remove"})
    @DeleteMapping
    public CommonResponse<String> folderRemove(@RequestBody LikeFolderRemoveRequestVo likeFolderRemoveRequestVo){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        LikeFolderDto likeFolderDto = LikeFolderDto.voToDto(likeFolderRemoveRequestVo);
        likeFolderService.removeLikeFolder(likeFolderDto, memberUuid);
        return CommonResponse.success("폴더 삭제 성공");
    }

    @Operation(summary = "폴더 수정", description = "폴더 수정", tags = {"like folder modify"})
    @PatchMapping
    public CommonResponse<String> folderModify(@RequestBody LikeFolderModifyRequestVo likeFolderModifyRequestVo){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        LikeFolderDto likeFolderDto = LikeFolderDto.voToDto(likeFolderModifyRequestVo);
        likeFolderService.modifyLikeFolder(likeFolderDto, memberUuid);
        return CommonResponse.success("폴더 수정 성공");
    }

    @Operation(summary = "선택한 폴더에 좋아요 상품 추가", description = "선택한 폴더에 좋아요 상품 추가", tags = {"product like add folder"})
    @PostMapping("/product")
    public CommonResponse<String> productLikeAddFolder(@RequestBody ProductLikeMoveRequestVo productLikeMoveRequestVo){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        LikeAddFolderDto likeAddFolderDto = LikeAddFolderDto.voToDto(productLikeMoveRequestVo);
        likeFolderService.addProductToLikeFolder(likeAddFolderDto, memberUuid);
        return CommonResponse.success("폴더 추가 성공");
    }




}
