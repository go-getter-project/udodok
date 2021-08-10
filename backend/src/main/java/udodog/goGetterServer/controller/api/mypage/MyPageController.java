package udodog.goGetterServer.controller.api.mypage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.converter.mypage.MyPageConverter;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.mypage.MyPageRequestDto;
import udodog.goGetterServer.model.dto.response.mypage.MyPageResponseDto;
import udodog.goGetterServer.service.mypage.MyPageService;

@Api(tags = {"마이 페이지 관련 API"})
@RequiredArgsConstructor
@RestController
public class MyPageController {

    private final MyPageService myPageService;
    private final MyPageConverter myPageConverter;

    @ApiOperation(value = "마이 페이지 유저 상세 조회 API",notes = "유저 상세 조회 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 조회성공 \t\n 2. 데이터없음 \t\n 3. 토큰에러")
    })

    // 회원 정보 상세 보기
    @GetMapping("/api/bkusers/mypage/{userId}")
    public ResponseEntity<EntityModel<DefaultRes<MyPageResponseDto>>> getDetailUser(
            @PathVariable("userId") Long userId
    ){
        return new ResponseEntity<>(myPageConverter.toModel(myPageService.getUserInfo(userId)), HttpStatus.OK);
    }

    @ApiOperation(value = "마이페이지 유저 정보 수정 API",notes = "유저 정보 수정 API입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 수정성공 \t\n 2. 수정실패 \t\n 3. 데이터없음 \t\n 4. 토큰에러")
    })

    // 회원 정보 수정
    @PatchMapping("/api/bkusers/mypage/edit/{userId}")
    public ResponseEntity<EntityModel<DefaultRes<MyPageRequestDto>>> updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody MyPageRequestDto requestDto
    ) {
        return new ResponseEntity<>(myPageConverter.toModel(myPageService.updateUserInfo(userId, requestDto)), HttpStatus.OK);
    }
}
