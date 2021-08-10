package udodog.goGetterServer.model.dto.response.coupon.selectbox;

import lombok.Getter;

@Getter
public class CouponSelectBoxResponseDto {

    private Long couponId;

    private String couponName;

    public CouponSelectBoxResponseDto(Long couponId, String couponName) {
        this.couponId = couponId;
        this.couponName = couponName;
    }
}
