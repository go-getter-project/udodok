package udodog.goGetterServer.controller.api.coupon;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.request.coupon.CouponCreateRequestDto;
import udodog.goGetterServer.service.coupon.CouponService;

import javax.validation.Valid;

@Api(tags = {"쿠폰 관련 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CouponController {

    private final CouponService couponService;

    @ApiOperation(value = "쿠폰 등록 API",notes = "관리자가 쿠폰 등록시 사용되는 API 입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 등록성공")
    })
    @PostMapping("/admin/coupons")
    public ResponseEntity<DefaultRes> couponCreate(
            @Valid @RequestBody CouponCreateRequestDto request
            ){
        return new ResponseEntity<>(couponService.couponCreate(request), HttpStatus.OK);
    }

    @ApiOperation(value = "쿠폰 다운로드 API", notes = "사용자가 쿠폰 다운로드시 사용되는 API 입니다. \n *동일한 쿠폰을 중복해서 다운로드 불가 \n *쿠폰 수량이 0일 경우 다운로드 불가")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 다운로드성공 \n 2. 실패(중복) \n 3. 실패(수량0) \n 4. 데이터없음")
    })
    @GetMapping("/users/coupons")
    public ResponseEntity<DefaultRes> couponDownload(
            @RequestParam("userId") Long userId,
            @RequestParam("couponId") Long couponId
    ){
        return new ResponseEntity<>(couponService.couponDownload(userId, couponId), HttpStatus.OK);
    }


}
