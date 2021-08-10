package udodog.goGetterServer.controller.api.coupon.selectbox;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udodog.goGetterServer.model.dto.DefaultRes;

import udodog.goGetterServer.model.dto.response.coupon.selectbox.CouponSelectBoxResponseDto;
import udodog.goGetterServer.service.coupon.CouponService;

import java.util.List;


@Api(tags = {"이벤트 등록시, 쿠폰 조회 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CouponSelectBoxController {

    private final CouponService couponService;

    @ApiOperation(value = "쿠폰명 조회 API",notes = "쿠폰 등록시 셀렉트박스 요소에 필요한 데이터를 조회하는 API 입니다.")
    @ApiResponses(value ={
            @ApiResponse(code=200, message = "1. 조회성공 \n 2. 데이터없음")
    })
    @GetMapping("/admin/events/coupons")
    public ResponseEntity<DefaultRes<List<CouponSelectBoxResponseDto>>> couponSelectBox(){
        return new ResponseEntity<>(couponService.couponSelectBox(), HttpStatus.OK);
    }
}
